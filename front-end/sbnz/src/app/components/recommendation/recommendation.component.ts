import { Component } from '@angular/core';
import { Subscription } from 'rxjs';
import { ArrangementRecommendation } from 'src/app/model/arrangment-recommendation';
import { UserLogged } from 'src/app/model/user-logged.model';
import { ArrangementService } from 'src/app/services/arrangement-service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-recommendation',
  templateUrl: './recommendation.component.html',
  styleUrls: ['./recommendation.component.css']
})
export class RecommendationComponent {
  arrangementRecommendations: ArrangementRecommendation[];
  loggedUser: UserLogged | undefined = undefined;
  subscription: Subscription;

  constructor(
    private arrService: ArrangementService,
    private authService: AuthService,
  ) {}

  ngOnInit(): void {
    this.subscription = this.authService.loggedUser.subscribe((user) => {
      this.loggedUser = user;
      this.GetArrangementRecommendations();
    });
  }
  GetArrangementRecommendations() {
    this.arrService.getArrangementRecommendationsWithoutUser().subscribe({
      next: (result: ArrangementRecommendation[]) => {
        this.arrangementRecommendations = result;
        console.log(this.arrangementRecommendations);
      }
    });
  }





}
