import { Component, OnInit } from '@angular/core';
import { UserLogged } from 'src/app/model/user-logged.model';
import { ArrangementService } from 'src/app/services/arrangement-service';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { UserPreferences } from 'src/app/model/user-preferences';


@Component({
  selector: 'app-preferences',
  templateUrl: './preferences.component.html',
  styleUrls: ['./preferences.component.css']
})
export class PreferencesComponent implements OnInit {
  userId: number;
  userPreferences: UserPreferences | undefined;
  loggedUser: UserLogged | undefined = undefined;  
  subscription: Subscription;

  selectedDestinations: string[] = [];
  selectedTripTypes: string[] = [];
  destinations: string[] = [
    'Paris', 'Rome', 'Barcelona', 'Tokyo', 'New York', 'Sydney', 'Dubai', 'London', 'Athens', 'Cairo'
  ];
  tripTypes = [
    { label: 'History', value: 'HISTORY' },
    { label: 'Cultural', value: 'CULTURAL' },
    { label: 'Gastronomic', value: 'GASTRONOMIC' },
    { label: 'Sports', value: 'SPORTS' },
    { label: 'Relaxation', value: 'RELAXATION' },
    { label: 'Diving', value: 'DIVING' },
    { label: 'Hiking', value: 'HIKING' }
  ];
  
  constructor(private arrangementService: ArrangementService, private authService: AuthService) {}

  ngOnInit(): void {
    this.subscription = this.authService.loggedUser.subscribe((user) => {
      this.loggedUser = user;  
      if (user) {
        this.userId = user.id;
        this.getUserPreferences();
      }
    });
  }
  
  getUserPreferences() {
    this.arrangementService.getUserPreferencesByUserId(this.userId)
      .subscribe(
        response => {
          this.userPreferences = response;
          // If user preferences exist, set selected destinations and trip types
          if (this.userPreferences) {
            this.selectedDestinations = this.userPreferences.destinations;
            this.selectedTripTypes = this.userPreferences.trips;
          }
        },
        error => {
          console.error('Error fetching user preferences:', error);
        }
      );
  }

  saveUserPreferences() {
    const userPreferences: UserPreferences = {
      userId: this.userId,
      id:0,
      destinations: this.selectedDestinations,
      trips: this.selectedTripTypes
    };
    this.arrangementService.createUserPreferences(this.userId,userPreferences)
      .subscribe(
        response => {
          this.userPreferences = response;
          console.log('User preferences saved successfully:', response);
        },
        error => {
          console.error('Error saving user preferences:', error);
        }
      );
  }

  isFormValid(): boolean {
    const destinationsCount = this.selectedDestinations.length;
    const tripTypesCount = this.selectedTripTypes.length;
    return destinationsCount >= 7 && destinationsCount <= 10 && tripTypesCount === 3;
  }
}
