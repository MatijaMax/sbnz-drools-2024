import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ArrangementWithTrips } from 'src/app/model/arrangement-with-trips';
import { UserLogged } from 'src/app/model/user-logged.model';
import { ArrangementService } from 'src/app/services/arrangement-service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-boilerplate',
  templateUrl: './boilerplate.component.html',
  styleUrls: ['./boilerplate.component.css'],
})
export class BoilerplateComponent implements OnInit {
  arrangement1: ArrangementWithTrips = { name: '', price: 0, id: 0, trips: [] };
  arrangementsAll: ArrangementWithTrips[];
  loggedUser: UserLogged | undefined = undefined;
  subscription: Subscription;

  constructor(
    private arrService: ArrangementService,
    private router: Router,
    private authService: AuthService,
  ) {}
  ngOnInit(): void {
    this.GetArrangements();
    this.subscription = this.authService.loggedUser.subscribe((user) => {
      this.loggedUser = user;
    });
  }

  GetArrangements() {
    this.arrService.getAllWithTrips().subscribe({
      next: (result: ArrangementWithTrips[]) => {
        this.arrangementsAll = result;
      },
    });
  }
}
