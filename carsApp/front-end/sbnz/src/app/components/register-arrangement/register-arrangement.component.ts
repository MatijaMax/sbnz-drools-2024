import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Car } from 'src/app/model/car';
import { CarCreate } from 'src/app/model/carCreate';
import { CarService } from 'src/app/services/cars-service';

@Component({
  selector: 'app-register-arrangement',
  templateUrl: './register-arrangement.component.html',
  styleUrls: ['./register-arrangement.component.css'],
})
export class RegisterArrangementComponent {
  registerForm = new FormGroup({
    brand: new FormControl('', [Validators.required]),
    model: new FormControl('', [Validators.required]),
    price: new FormControl(0, [Validators.required]),
    type: new FormControl('', [Validators.required]),
    engineType: new FormControl('', [Validators.required]),
  });
  car: CarCreate = { model: '', price: 0, brand: '', engineType: '', type: '' };

  constructor(private arrService: CarService, private router: Router) {}
  ngOnInit(): void {}

  RegisterCar() {
    this.fillArr();
    this.arrService.registerCar(this.car).subscribe({
      next: (result: Car) => {
        this.router.navigate(['/boilerplate']);
        console.log(result);
        alert('car registered');
      },
    });
  }
  fillArr() {
    this.car.model = this.registerForm.value.model || '';
    this.car.type = this.registerForm.value.type || '';
    this.car.engineType = this.registerForm.value.engineType || '';
    this.car.brand = this.registerForm.value.brand || '';
    this.car.price = this.registerForm.value.price || 0;
  }
}
