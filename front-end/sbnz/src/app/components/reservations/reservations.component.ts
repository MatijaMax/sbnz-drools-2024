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

  getTripNames(arrangementId: number): string[] {
    const arrangement = this.arrangementsAll.find(a => a.id === arrangementId);
    return arrangement ? arrangement.trips.map(trip => trip.name) : [];
  }

  getTotalNumberOfPeopleGoing(arrangementId: number): number {
    const arrangementReservation = this.reservations.find(r => r.arrangementId === arrangementId);
    return arrangementReservation ? arrangementReservation.numberOfPeople : 0;
  }
}
