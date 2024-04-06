import { Component } from '@angular/core';
import { UserLogin } from 'src/app/model/user-login.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserServiceService } from 'src/app/services/user-service.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  hide = true
  user : UserLogin = {email: "", password: ""}
  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  constructor(private authService: AuthService, private router: Router){}
  ngOnInit(): void {
  }

  LoginUser(){
    this.user.email = this.loginForm.value.email || ""
    this.user.password = this.loginForm.value.password || ""
    this.authService.login(this.user).subscribe({
      next: () => { 
        alert('Succesfull login')
        this.router.navigate(['/boilerplate'])
       },
      error: () => {
        alert('Login error, wrong username or password')
      }
    });
  }
}
