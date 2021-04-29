import { ListCongesComponent } from './conges/list-conges/list-conges.component';
import { HttpClientModule } from '@angular/common/http';
import { SharedModule } from './shared/shared-module';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DemandeComponent } from './demande/demande.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './auth/login/login.component';
import { HomeComponent } from './home/home.component';
import { ListcongesManagerComponent } from './conges/listconges-manager/listconges-manager.component';
import { RouterModule } from '@angular/router';
import { MAT_DATE_LOCALE } from '@angular/material/core';
import { DatePipe } from '@angular/common';
import { CreateloginComponent } from './auth/createlogin/createlogin.component';
import { CreateemployeComponent } from './employe/createemploye/createemploye.component';
import { FormControl } from '@angular/forms';
const MY_FORMATS = {
  parse: {
    dateInput: 'DD MMMM YYYY',
  },
  display: {
    dateInput: 'DD MMMM YYYY',
    monthYearLabel: 'MMMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};

@NgModule({
  declarations: [
    AppComponent,
    DemandeComponent,
    LoginComponent,
    HomeComponent,
    ListcongesManagerComponent,
    ListCongesComponent,
    CreateloginComponent,
    CreateemployeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    SharedModule,
    HttpClientModule,
    RouterModule,
  ],
  providers: [
    { provide: MAT_DATE_LOCALE, useValue: 'fr-FR' },
    DatePipe,
    FormControl,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
