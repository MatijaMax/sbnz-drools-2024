import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ArrangementWithTrips } from 'src/app/model/arrangement-with-trips';
import { ArrangementService } from 'src/app/services/arrangement-service';

@Component({
  selector: 'app-boilerplate',
  templateUrl: './boilerplate.component.html',
  styleUrls: ['./boilerplate.component.css'],
})
export class BoilerplateComponent {
  arrangement1: ArrangementWithTrips = { name: '', price: 0, id: 0, trips: [] };
  arrangementsAll: ArrangementWithTrips[];

  constructor(private arrService: ArrangementService, private router: Router) {}
  ngOnInit(): void {
    this.GetArrangements();
  }

  GetArrangements() {
    this.arrService.getAllWithTrips().subscribe({
      next: (result: ArrangementWithTrips[]) => {
        this.arrangementsAll = result;
      },
    });
  }
}
