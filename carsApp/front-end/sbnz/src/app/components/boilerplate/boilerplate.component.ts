import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Car } from 'src/app/model/car';
import { UserLogged } from 'src/app/model/user-logged.model';
import { CarService } from 'src/app/services/cars-service';
import { AuthService } from 'src/app/services/auth.service';
import { RentRequest } from 'src/app/model/rentRequest';
import { FormBuilder, FormGroup } from '@angular/forms';

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
    returnStateHelp: '',
    id: 0,
    userId: 0,
    carId: 0,
    beginDT: new Date(),
    cancelDT: new Date(),
    cancelReason: '',
    canceled: false,
    late: false,
    returnDT: new Date(),
    returnState: '',
    scheduleDT: new Date(),
  };
  dateTimeForm: FormGroup;

  constructor(
    private carrService: CarService,
    private router: Router,
    private authService: AuthService,
    private fb: FormBuilder
  ) {
    this.dateTimeForm = this.fb.group({
      dateB: [''],
      timeB: [''],
      dateR: [''],
      timeR: [''],
    });
  }
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
    let date = this.dateTimeForm.value.dateB;
    let time = this.dateTimeForm.value.timeB;

    let d = new Date(date + 'T' + time);
    let timeZoneDifference = (d.getTimezoneOffset() / 60) * -1;
    d.setTime(d.getTime() + timeZoneDifference * 60 * 60 * 1000);
    this.rentRequest.beginDT = d;

    date = this.dateTimeForm.value.dateR;
    time = this.dateTimeForm.value.timeR;
    d = new Date(date + 'T' + time);
    timeZoneDifference = (d.getTimezoneOffset() / 60) * -1;
    d.setTime(d.getTime() + timeZoneDifference * 60 * 60 * 1000);
    this.rentRequest.returnDT = d;

    console.log(d);
    // return;
    this.rentRequest.userId = this.loggedUser?.id;
    this.rentRequest.carId = c.id;
    this.carrService.rentRequestCreate(this.rentRequest).subscribe({
      next: (result: RentRequest) => {
        console.log('AD');
      },
    });
  }
}
