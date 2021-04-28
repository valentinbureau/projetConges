import { Router } from '@angular/router';
import { AuthenticationService } from './../../services/authentication.service';
import { Component, OnInit } from '@angular/core';
import { Login } from 'src/app/model/login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  login = new Login();
  message: string;

  constructor(private AuthenticationService: AuthenticationService, private router : Router) { }

  ngOnInit(): void {
  }

  send(){
    this.AuthenticationService.getAuthApi(this.login).subscribe(
      (res) => {
        this.message = null;
        localStorage.setItem(
          'auth', btoa(`${this.login.login}:${this.login.password}`)
        );
        localStorage.setItem('login', this.login.login);
      },
      (error) => {
        this.message = "Compte inconnu ou mauvais mot de passe"
      }
    )
  }

}
