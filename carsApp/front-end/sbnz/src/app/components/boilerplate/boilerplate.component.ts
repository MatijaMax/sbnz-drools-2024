import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Car } from 'src/app/model/car';
import { UserLogged } from 'src/app/model/user-logged.model';
import { CarService } from 'src/app/services/cars-service';
import { AuthService } from 'src/app/services/auth.service';

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
}
