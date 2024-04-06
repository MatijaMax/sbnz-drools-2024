import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ArrangementCreate } from 'src/app/model/arrangement-create';
import { ArrangementService } from 'src/app/services/arrangement-service';

@Component({
  selector: 'app-register-arrangement',
  templateUrl: './register-arrangement.component.html',
  styleUrls: ['./register-arrangement.component.css'],
})
export class RegisterArrangementComponent {
  arrangement: ArrangementCreate = { name: '', price: 0 };
  registerForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    price: new FormControl(0, [Validators.required]),
  });

  constructor(private arrService: ArrangementService, private router: Router) {}
  ngOnInit(): void {}

  RegisterArr() {
    this.fillArr();
    this.arrService.registerArrangement(this.arrangement).subscribe({
      next: () => {
        alert('arrangement registered');
        this.router.navigate(['/boilerplate']);
      },
    });
  }
  fillArr() {
    this.arrangement.name = this.registerForm.value.name || '';
    this.arrangement.price = this.registerForm.value.price || 0;
  }
}
