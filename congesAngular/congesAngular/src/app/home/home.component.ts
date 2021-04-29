import { Login } from 'src/app/model/login';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {

  login: string;
  constructor() {}

  ngOnInit(): void {
    this.login = localStorage.getItem('login')
  }
}
