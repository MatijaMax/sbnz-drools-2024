import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
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
import { RegisterArrangementComponent } from './components/register-arrangement/register-arrangement.component';

const appRoutes: Routes = [
  { path: '', redirectTo: 'companies', pathMatch: 'full' },
  { path: 'boilerplate', component: BoilerplateComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'register-arrangement', component: RegisterArrangementComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    RegisterComponent,
    BoilerplateComponent,
    RegisterArrangementComponent,
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
    RouterModule.forRoot(appRoutes),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
