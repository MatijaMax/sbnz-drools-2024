import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Car } from 'src/app/model/car';
import { UserLogged } from 'src/app/model/user-logged.model';
import { CarService } from 'src/app/services/cars-service';
import { AuthService } from 'src/app/services/auth.service';
import { RentRequest } from 'src/app/model/rentRequest';

@Component({
  selector: 'app-boilerplate',
  templateUrl: './boilerplate.component.html',
  styleUrls: ['./boilerplate.component.css'],
})
export class BoilerplateComponent implements OnInit {
  arrangementsAll: Car[];
  loggedUser: UserLogged | undefined = undefined;
  subscription: Subscription;
  arrangementGrades: number[];
  rentRequest: RentRequest = {
    userId: 0,
    carId: 0,
    beginDT: new Date(),
    cancelDT: new Date(),
    cancelReason: '',
    isCanceled: false,
    isLate: false,
    returnDT: new Date(),
    returnState: '',
    scheduleDT: new Date(),
  };

  constructor(
    private carrService: CarService,
    private router: Router,
    private authService: AuthService
  ) {}
  ngOnInit(): void {
    this.GetArrangements();
    this.subscription = this.authService.loggedUser.subscribe((user) => {
      this.loggedUser = user;
    });
  }

  GetArrangements() {
    this.carrService.getAllCars().subscribe({
      next: (result: Car[]) => {
        this.arrangementsAll = result;
        this.arrangementGrades = Array.from(
          { length: this.arrangementsAll.length },
          () => 1
        );
      },
    });
  }

  rentCar(c: Car) {
    if (this.loggedUser?.id == undefined) {
      return;
    }
    this.rentRequest.userId = this.loggedUser?.id;
    this.rentRequest.carId = c.id;
    this.carrService.rentRequestCreate(this.rentRequest).subscribe({
      next: (result: RentRequest) => {
        console.log('AD');
      },
    });
  }
}
