import { ListcongesManagerComponent } from './conges/listconges-manager/listconges-manager.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { ListCongesComponent } from './conges/list-conges/list-conges.component';
import { DemandeComponent } from './demande/demande.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: 'conge/manager', component: ListcongesManagerComponent},
  { path: 'conge/employe/:login', component: ListCongesComponent},
  { path: 'demande/employe/:login', component: DemandeComponent},
  { path: 'login', component: LoginComponent},
  { path: 'home', component: HomeComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
