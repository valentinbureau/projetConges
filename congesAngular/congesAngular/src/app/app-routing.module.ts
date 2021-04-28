import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { DemandeComponent } from './demande/demande.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  //{ path: 'demande/employe/:id', component: },
  { path: 'demande/employe/:id/edit', component: DemandeComponent},
  { path: 'login', component: LoginComponent},
  { path: 'home', component: HomeComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
