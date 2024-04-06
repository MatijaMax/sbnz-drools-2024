import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ArrangementCreate } from '../model/arrangement-create';
import { Observable } from 'rxjs';
import { environment } from '../env/environment';

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
}
