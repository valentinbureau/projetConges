import { Login } from 'src/app/model/login';
import { Component, OnInit } from '@angular/core';
import { Employe } from '../model/employe';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  employe: string
  login: string;
  constructor() {}

  ngOnInit(): void {
    this.login = localStorage.getItem('login')
    this.employe = localStorage.getItem('employe');
    console.log(this.employe);
  }

  click(){
    localStorage.clear();
    this.ngOnInit();
  }
}
