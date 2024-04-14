import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ArrangementCreate } from '../model/arrangement-create';
import { Observable } from 'rxjs';
import { environment } from '../env/environment';
import { TripCreate } from '../model/trip-create';
import { ArrangementReservationCreate } from '../model/arrangement-reservation-create';
import { ArrangementGradeCreate } from '../model/arrangement-grade-create';

@Injectable({
  providedIn: 'root',
})
export class ArrangementService {
  constructor(private http: HttpClient) {}

  registerArrangement(arrangement: ArrangementCreate): Observable<any> {
    return this.http.post<any>(
      environment.apiHost + 'arrangements',
      arrangement
    );
  }

  getAllWithTrips(): Observable<any> {
    return this.http.get<any>(
      environment.apiHost + 'arrangements/allWithTrips'
    );
  }

  addTrip(t: TripCreate): Observable<any> {
    return this.http.post<any>(environment.apiHost + 'trips', t);
  }

  createArrangementReservation(arrangementReservation: ArrangementReservationCreate): Observable<any> {
    return this.http.post<any>(
      environment.apiHost + 'arrangementReservations',
      arrangementReservation
    );
  }

  rateArrangement(arrangementGrade: ArrangementGradeCreate) : Observable<any> {
    return this.http.post<any>(
      environment.apiHost + 'arrangementGrades',
      arrangementGrade
    )
  }
}
