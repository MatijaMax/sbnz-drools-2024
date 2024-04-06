import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Arrangement } from 'src/app/model/arrangement';
import { ArrangementCreate } from 'src/app/model/arrangement-create';
import { TripCreate } from 'src/app/model/trip-create';
import { ArrangementService } from 'src/app/services/arrangement-service';

@Component({
  selector: 'app-register-arrangement',
  templateUrl: './register-arrangement.component.html',
  styleUrls: ['./register-arrangement.component.css'],
})
export class RegisterArrangementComponent {
  arrangement: ArrangementCreate = { name: '', price: 0 };
  createdArrangement: Arrangement = { name: '', price: 0, id: 0 };
  trip: TripCreate = { name: '', price: 0, description: '', arrangementId: 0 };
  registerForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    price: new FormControl(0, [Validators.required]),
  });

  constructor(private arrService: ArrangementService, private router: Router) {}
  ngOnInit(): void {}

  RegisterArr() {
    this.fillArr();
    this.arrService.registerArrangement(this.arrangement).subscribe({
      next: (result: Arrangement) => {
        this.createdArrangement = result;
        //this.router.navigate(['/boilerplate']);
        console.log(this.createdArrangement);
        alert('arrangement registered');
      },
    });
  }
  fillArr() {
    this.arrangement.name = this.registerForm.value.name || '';
    this.arrangement.price = this.registerForm.value.price || 0;
  }
  AddTrip() {
    this.trip.arrangementId = this.createdArrangement.id;
    this.arrService.addTrip(this.trip).subscribe({
      next: () => {
        console.log(this.trip);
        alert('tour registered');
        this.trip = { name: '', price: 0, description: '', arrangementId: 0 };
      },
    });
  }
}
