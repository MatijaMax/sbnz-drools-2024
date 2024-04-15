import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ArrangementReservationResponseDTO } from 'src/app/model/arrangement-reservation-responce';
import { ArrangementWithTrips } from 'src/app/model/arrangement-with-trips';
import { Trip } from 'src/app/model/trip';
import { UserLogged } from 'src/app/model/user-logged.model';
import { ArrangementService } from 'src/app/services/arrangement-service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {
  arrangementsAll: ArrangementWithTrips[];
  reservations: ArrangementReservationResponseDTO[];
  displayedColumns: string[] = ['arrangementName', 'tripNames', 'numberOfPeopleGoing', 'totalPrice'];
  loggedUser: UserLogged | undefined = undefined;  
  subscription: Subscription;

  constructor(private arrangementService: ArrangementService, private authService: AuthService) {}

  ngOnInit(): void {
    this.subscription = this.authService.loggedUser.subscribe((user) => {
      this.loggedUser = user;
      this.fetchReservationsByUserId();    
    });
  }

  fetchReservationsByUserId(): void {
    const userId = this.loggedUser != undefined ? this.loggedUser?.id : -1;
    this.arrangementService.getReservationsByUserId(userId)
      .subscribe(
        (reservations: ArrangementReservationResponseDTO[]) => {
          this.reservations = reservations;
          // After fetching reservations, fetch arrangements to get arrangement names
          this.GetArrangements();
        },
        (error) => {
          console.error('Error fetching reservations:', error);
        }
      );
  }

  GetArrangements() {
    this.arrangementService.getAllWithTrips().subscribe({
      next: (result: ArrangementWithTrips[]) => {
        this.arrangementsAll = result;
      },
    });
  }

  getArrangementName(arrangementId: number): string {
    const arrangement = this.arrangementsAll.find(a => a.id === arrangementId);
    return arrangement ? arrangement.name : 'Unknown';
  }


  getTripName(tripId: number): string {
    const trip = this.getTripById(tripId);
    return trip ? trip.name : 'Unknown';
  }
  
  private getTripById(tripId: number): Trip | undefined {
    for (var arrangement of this.arrangementsAll) {
      var trip = arrangement.trips.find(t => t.id === tripId);
      if (trip) {
        return trip;
      }
    }
    return undefined;
  }
  
  getTripNumById(tripId: number, res: ArrangementReservationResponseDTO): number {
      console.log(res);
      console.log(tripId);
      var trip = res.tripReservations.find(t => t.tripId === tripId);
      if (trip) {
        return trip.numberOfGuests;
      }

      return 0;
  }
}
