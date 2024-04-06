import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserLogged } from 'src/app/model/user-logged.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{
  constructor(private authService: AuthService, private router: Router){}
  user: UserLogged | undefined
  ngOnInit(): void {
    this.authService.loggedUser.subscribe(user => {
      this.user = user;
    });
  }
  logout(){
    this.authService.logout()
    this.router.navigate(['/login']);
    this.user = undefined
  }
}
