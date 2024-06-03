import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { RentRequest } from 'src/app/model/rentRequest';
import { Suspects } from 'src/app/model/susDto';
import { UserLogged } from 'src/app/model/user-logged.model';
import { AuthService } from 'src/app/services/auth.service';
import { CarService } from 'src/app/services/cars-service';

@Component({
  selector: 'app-retutning-rentings',
  templateUrl: './retutning-rentings.component.html',
  styleUrls: ['./retutning-rentings.component.css'],
})
export class RetutningRentingsComponent {
  loggedUser: UserLogged | undefined = undefined;
  suspiciousUsers: number[] = [];
  subscription: Subscription;
  retingsRequests: RentRequest[];
  constructor(
    private carrService: CarService,
    private authService: AuthService
  ) {}
  ngOnInit(): void {
    this.subscription = this.authService.loggedUser.subscribe((user) => {
      this.loggedUser = user;
      this.GetRRequests();
    });
  }

  LoadSus() {
    if (this.loggedUser?.id == undefined) {
      return;
    }
    this.carrService.getSus().subscribe({
      next: (result: Suspects) => {
        this.suspiciousUsers = result.ids;
      },
    });
  }
  GetRRequests() {
    if (this.loggedUser?.id == undefined) {
      return;
    }
    this.carrService.getAllRentRequests().subscribe({
      next: (result: RentRequest[]) => {
        this.LoadSus();
        this.retingsRequests = result;
      },
    });
  }

  returnThem(r: RentRequest) {
    r.returnState = r.returnStateHelp;
    this.carrService.returnRRequest(r).subscribe({
      next: (result: RentRequest) => {
        this.GetRRequests();
      },
    });
  }

  isSuspiciousUser(userId: number): boolean {
    return this.suspiciousUsers.includes(userId);
  }
}
