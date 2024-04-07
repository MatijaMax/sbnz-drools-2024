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

  buyArrangement(arrangement: ArrangementWithTrips) {
    // Construct the DTO object
    const arrangementReservation: ArrangementReservationCreate = {
      id: 0, // This should be replaced by the actual ID if needed
      arrangementId: arrangement.id,
      //TODO: FIX USER ID
      userId: 1,
      numberOfPeople: 1, // Assuming the default number of people is 1
      tripReservations: arrangement.trips.map((trip) => ({
        id: 0, // This should be replaced by the actual ID if needed
        arrangementReservationId: 0, // This will be replaced by the backend
        tripId: trip.id,
        numberOfGuests: 1, // Assuming the default number of guests is 1
      })),
    };

    // Send the DTO to the backend
    this.arrService.createArrangementReservation(arrangementReservation).subscribe({
      next: (response) => {
        console.log('Arrangement reservation created:', response);
        // Optionally, you can redirect the user to a success page or do other actions
      },
      error: (error) => {
        console.error('Error creating arrangement reservation:', error);
        // Handle the error as needed
      },
    });
  }
}
