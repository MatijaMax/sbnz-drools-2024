import { Injectable } from '@angular/core';
import { BehaviorSubject, map } from 'rxjs';
import { UserLogged } from '../model/user-logged.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from '../env/environment';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  loggedUser = new BehaviorSubject<UserLogged>({
    email: "",
    role: "",
    id: -1,
  })
  private access_token = null;
  constructor(
    private http: HttpClient,
    private router: Router,
  ) { }
  login(user:any) {
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    const body = {
      'email': user.email,
      'password': user.password
    };
    let options = { headers: loginHeaders };
    return this.http.post<any>(environment.apiHost + "auth/login", JSON.stringify(body),
      options
    )
    .pipe(map((res) => {
      console.log('Login success');
      this.access_token = res.accessToken;
      localStorage.setItem("jwt", res.accessToken)
      this.setUser()
      this.router.navigate(['/boilerplate'])
    }));
  }
  tokenIsPresent() {
    return this.access_token != undefined && this.access_token != null;
  }
  getToken() {
    return this.access_token;
  }
  logout() {
    localStorage.removeItem("jwt");
    this.access_token = null;
    this.router.navigate(['/login']);
    this.loggedUser.next({ email: "", role: "" , id: -1});
  }
  setUser(){
    let token = localStorage.getItem("jwt")
    const jwtHelperService = new JwtHelperService();
    const user: UserLogged = {
      email: jwtHelperService.decodeToken(token!).email,
      role: jwtHelperService.decodeToken(token!).role,
      id: jwtHelperService.decodeToken(token!).id,
    }
    console.log(user)
    this.loggedUser.next(user)
  }
}
