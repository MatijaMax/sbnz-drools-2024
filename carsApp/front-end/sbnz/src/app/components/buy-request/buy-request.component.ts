import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { BuyRequest } from 'src/app/model/buyRequest';
import { UserLogged } from 'src/app/model/user-logged.model';
import { AuthService } from 'src/app/services/auth.service';
import { CarService } from 'src/app/services/cars-service';

@Component({
  selector: 'app-buy-request',
  templateUrl: './buy-request.component.html',
  styleUrls: ['./buy-request.component.css']
})
export class BuyRequestsComponent implements OnInit {
  buyRequests: BuyRequest[] = [];
  loggedUser: UserLogged | undefined = undefined;
  subscription: Subscription;
  amount: number = 0;

  constructor(private carService: CarService, private authService: AuthService,private router: Router) {}

  ngOnInit(): void {
    this.subscription = this.authService.loggedUser.subscribe((user) => {
      this.loggedUser = user;
      if (this.loggedUser?.id) {
        this.loadBuyRequests(this.loggedUser.id);
      }
    });
  }

  loadBuyRequests(userId: number): void {
    this.carService.getBuyRequestsByUser(userId).subscribe({
      next: (buyRequests: BuyRequest[]) => {
        this.buyRequests = buyRequests;
      },
      error: (err) => {
        console.error('Error fetching buy requests:', err);
      }
    });
  }

  payRequest(requestId: number, amount: number): void {
    if (!amount || amount <= 0) {
      console.error('Amount must be greater than 0');
      return;
    }

    this.carService.payBuyRequest(requestId, amount).subscribe({
      next: (response) => {
        console.log('Payment successful:', response);
        this.loadBuyRequests(this.loggedUser!.id); 
      },
      error: (err) => {
        console.error('Error processing payment:', err);
        this.loadBuyRequests(this.loggedUser!.id); 
      }
    });
  }
}
