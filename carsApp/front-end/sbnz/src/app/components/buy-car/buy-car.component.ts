import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CarService } from 'src/app/services/cars-service';
import { BuyRequest } from 'src/app/model/buyRequest';
import { UserLogged } from 'src/app/model/user-logged.model';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-buy-car',
  templateUrl: './buy-car.component.html',
  styleUrls: ['./buy-car.component.css']
})
export class BuyCarComponent implements OnInit {
  buyCarForm: FormGroup;
  carId: string | null;
  loggedUser: UserLogged | undefined = undefined;
  subscription: Subscription;

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private carService: CarService,
    private authService: AuthService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.subscription = this.authService.loggedUser.subscribe((user) => {
      this.loggedUser = user;
    });
    this.carId = this.route.snapshot.paramMap.get('id');
    this.initializeForm();
  }

  initializeForm() {
    this.buyCarForm = this.fb.group({
      userAge: ['', Validators.required],
      numberOfCreditPayments: ['', Validators.required],
      useremploymenttype: ['', Validators.required],
      employmentStart: [''],
      employmentEnd: ['']
    });
  }

  onSubmit() {
    if (this.loggedUser?.id == undefined) {
      return;
    }
    if (this.buyCarForm.valid) {
      const formValues = this.buyCarForm.value;
      const buyRequest: BuyRequest = {
        id: 0,
        userId: this.loggedUser?.id,
        userAge: formValues.userAge,
        carId: Number(this.carId),
        numberOfCreditPayments: formValues.numberOfCreditPayments,
        useremploymenttype: formValues.useremploymenttype,
        employmentStart: formValues.employmentStart,
        employmentEnd: formValues.employmentEnd,
        leftToPay: 0,
      };

      this.carService.createBuyRequest(buyRequest).subscribe({
        next: (result) => {
          console.log('Buy request created:', result);
          this.router.navigate(['/boilerplate']);
        },
        error: (err) => {
          console.error('Error creating buy request:', err);
        }
      });
    }
  }
}
