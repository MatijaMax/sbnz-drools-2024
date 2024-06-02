import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RegisterComponent } from './components/register/register.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { BoilerplateComponent } from './components/boilerplate/boilerplate.component';
import { LoginComponent } from './components/login/login.component';
import { TokenInterceptor } from './interceptor/TokenInterceptor';
import { UserServiceService } from './services/user-service.service';
import { RegisterArrangementComponent } from './components/register-arrangement/register-arrangement.component';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { RentingsComponent } from './components/rentings/rentings.component';
import { MyRentingsComponent } from './components/my-rentings/my-rentings.component';
import { RetutningRentingsComponent } from './components/retutning-rentings/retutning-rentings.component';

const appRoutes: Routes = [
  { path: '', redirectTo: 'companies', pathMatch: 'full' },
  { path: 'boilerplate', component: BoilerplateComponent },
  { path: 'my-rentings', component: MyRentingsComponent },
  { path: 'return-rentings', component: RetutningRentingsComponent },
  { path: 'rentings', component: RentingsComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register-car', component: RegisterArrangementComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    RegisterComponent,
    BoilerplateComponent,
    LoginComponent,
    RegisterArrangementComponent,
    RentingsComponent,
    RentingsComponent,
    MyRentingsComponent,
    RetutningRentingsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    FormsModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatTableModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
    UserServiceService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
