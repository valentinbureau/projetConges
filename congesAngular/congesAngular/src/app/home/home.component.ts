import { EnumRole } from './../model/enum-role.enum';
import { RoleLoginService } from './../Services/role-login.service';
import { RoleLogin } from './../model/role-login';
import { Login } from 'src/app/model/login';
import { Component, OnInit } from '@angular/core';
import { Employe } from '../model/employe';
import { Observable } from 'rxjs';
import { ServiceEmployesService } from '../services/service-employes.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  roleLogin: Observable<RoleLogin>;
  login: string;
  employeConnecte: Employe;
  role: EnumRole;

  isAuth: boolean = false;
  isManager: boolean;
  constructor(private roleLoginService: RoleLoginService,
    private serviceEmployeService: ServiceEmployesService) {}

  ngOnInit(): void {
    this.login = localStorage.getItem('login')
    this.employeConnecte = JSON.parse(localStorage.getItem('employe'))
    this.roleLoginService.getRoleLoginbyLogin(this.login).subscribe(res=> {
      this.role=res.role;
    });
    this.serviceEmployeService.getManagerLogin(this.employeConnecte.service.id).subscribe(res=>{
      console.log(res);
      if (this.employeConnecte.id === res.id){
        this.isManager=true;
      }
      console.log(this.isManager);
    });

    if (localStorage.getItem('auth')!== 'dG90bzp0b3Rv'){
      this.isAuth =true;
      console.log(localStorage.getItem('auth'));
    }
  }

  isAdmin(){
    return (this.role===EnumRole.ROLE_ADMIN)
  }

}
