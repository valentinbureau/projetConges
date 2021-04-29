import { ServiceEmployesService } from './../../Services/service-employes.service';
import { RoleLoginService } from './../../Services/role-login.service';
import { Router } from '@angular/router';
import { AuthenticationService } from './../../services/authentication.service';
import { Component, OnInit } from '@angular/core';
import { Login } from 'src/app/model/login';
import { Employe } from 'src/app/model/employe';
import { EnumRole } from 'src/app/model/enum-role.enum';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  login = new Login();
  message: string;

  employeConnecte: Employe;
  role: EnumRole;
  isManager: boolean;

  constructor(
    private AuthenticationService: AuthenticationService,
    private router: Router,
    private roleLoginService: RoleLoginService,
    private serviceEmployeService: ServiceEmployesService
  ) {}

  ngOnInit(): void {
    this.employeConnecte = JSON.parse(localStorage.getItem('employe'))
    this.roleLoginService.getRoleLoginbyLogin(localStorage.getItem('login')).subscribe(res=> {
      this.role=res.role;
    });
    this.serviceEmployeService.getManagerLogin(this.employeConnecte.service.id).subscribe(res=>{
      console.log(res);
      if (this.employeConnecte.id === res.id){
        this.isManager=true;
      }
    });
  }
  isAdmin(){
    return (this.role===EnumRole.ROLE_ADMIN)
  }

  send() {
    this.AuthenticationService.getAuthApi(this.login).subscribe(
      (res) => {
        this.message = null;
        localStorage.setItem(
          'auth',
          btoa(`${this.login.login}:${this.login.password}`)
        );
        localStorage.setItem('login', this.login.login);
        this.AuthenticationService.getEmploye(this.login).subscribe((data) => {
          localStorage.setItem('employe', JSON.stringify(data));
          console.log(localStorage.getItem('employe'));
        });
        if (this.isManager){
          this.router.navigate(['/conge/manager/'])
        }
        else if (this.isAdmin()){

        }
        else {
          this.router.navigate(['/conge/employe/', this.login.login])
        }

      },
      (error) => {
        this.message = 'Compte inconnu ou mauvais mot de passe';
      }
    );
  }
}
