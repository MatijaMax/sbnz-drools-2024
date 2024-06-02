import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../env/environment';
import { CarCreate } from '../model/carCreate';
import { RentRequest } from '../model/rentRequest';

@Injectable({
  providedIn: 'root',
})
export class CarService {
  constructor(private http: HttpClient) {}

  registerCar(arrangement: CarCreate): Observable<any> {
    return this.http.post<any>(environment.apiHost + 'cars', arrangement);
  }

  getAllCars(): Observable<any> {
    return this.http.get<any>(environment.apiHost + 'cars/all');
  }

  rentRequestCreate(r: RentRequest): Observable<any> {
    return this.http.post<any>(environment.apiHost + 'rents', r);
  }

  cancelRRequest(r: RentRequest): Observable<any> {
    return this.http.post<any>(environment.apiHost + 'rents/cancel', r);
  }

  returnRRequest(r: RentRequest): Observable<any> {
    return this.http.post<any>(environment.apiHost + 'rents/returnRenting', r);
  }

  getAllRentRequests(): Observable<any> {
    return this.http.get<any>(environment.apiHost + 'rents/all');
  }

  getAllRentRequestsByUser(id: number): Observable<any> {
    return this.http.get<any>(environment.apiHost + 'rents/all/' + id);
  }
}
