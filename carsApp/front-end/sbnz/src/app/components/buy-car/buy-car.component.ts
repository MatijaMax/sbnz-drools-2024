import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-buy-car',
  templateUrl: './buy-car.component.html',
  styleUrls: ['./buy-car.component.css']
})
export class BuyCarComponent implements OnInit {
  buyCarForm: FormGroup;
  carId: string | null;

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
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
    if (this.buyCarForm.valid) {
      const formValues = this.buyCarForm.value;
      console.log('Form Values:', formValues);
      // Here you would typically handle the form submission, e.g., send data to backend
    }
  }
}
