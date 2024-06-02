import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { RentRequest } from 'src/app/model/rentRequest';
import { UserLogged } from 'src/app/model/user-logged.model';
import { AuthService } from 'src/app/services/auth.service';
import { CarService } from 'src/app/services/cars-service';

@Component({
  selector: 'app-my-rentings',
  templateUrl: './my-rentings.component.html',
  styleUrls: ['./my-rentings.component.css'],
})
export class MyRentingsComponent {
  loggedUser: UserLogged | undefined = undefined;
  subscription: Subscription;
  retingsRequests: RentRequest[];
  constructor(
    private carrService: CarService,
    private router: Router,
    private authService: AuthService
  ) {}
  ngOnInit(): void {
    this.subscription = this.authService.loggedUser.subscribe((user) => {
      this.loggedUser = user;
      this.GetRRequests();
    });
  }
  GetRRequests() {
    if (this.loggedUser?.id == undefined) {
      return;
    }
    this.carrService.getAllRentRequestsByUser(this.loggedUser?.id).subscribe({
      next: (result: RentRequest[]) => {
        this.retingsRequests = result;
      },
    });
  }

  cancel(r: RentRequest) {
    this.carrService.cancelRRequest(r).subscribe({
      next: (result: RentRequest) => {
        this.GetRRequests();
      },
    });
  }
}
