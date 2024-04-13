import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ArrangementWithTrips } from 'src/app/model/arrangement-with-trips';
import { UserLogged } from 'src/app/model/user-logged.model';
import { ArrangementService } from 'src/app/services/arrangement-service';
import { AuthService } from 'src/app/services/auth.service';
import { ArrangementReservationCreate } from 'src/app/model/arrangement-reservation-create'; // Import the DTO model

@Component({
  selector: 'app-boilerplate',
  templateUrl: './boilerplate.component.html',
  styleUrls: ['./boilerplate.component.css'],
})
export class BoilerplateComponent implements OnInit {
  arrangementsAll: ArrangementWithTrips[];
  loggedUser: UserLogged | undefined = undefined;
  subscription: Subscription;
  tripNumberOfGuests: number[];

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

  buyArrangement(arrangement: ArrangementWithTrips) {
    const arrangementReservation: ArrangementReservationCreate = {
      arrangementId: arrangement.id,
      userId: this.loggedUser != undefined? this.loggedUser?.id : -1,
      numberOfPeople: arrangement.numberOfGuests,
      tripReservations: arrangement.trips.map((trip) => ({
        tripId: trip.id,
        numberOfGuests: trip.numberOfGuests,
      })),
    };

    this.arrService.createArrangementReservation(arrangementReservation).subscribe({
      next: (response) => {
        alert('Arrangement reservation succesfully created');
        console.log('Arrangement reservation created:', response);
      },
      error: (error) => {
        alert('Error creating arrangement reservation');
        console.error('Error creating arrangement reservation:', error);
      },
    });
  }
}
