import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Conge } from 'src/app/model/conge';
import { Login } from 'src/app/model/login';
import { CongesService } from 'src/app/Services/conges.service';

@Component({
  selector: 'app-list-conges',
  templateUrl: './list-conges.component.html',
  styleUrls: ['./list-conges.component.css']
})
export class ListCongesComponent implements OnInit {

  conges: Conge[];
  login: string = localStorage.getItem('login');

  constructor(
    private congeService: CongesService,
  ) { }

  ngOnInit(): void {
    this.list();
  }

  private list() {
    console.log(this.login);
    this.congeService.findAllbyLogin(this.login).subscribe((data) => {
      this.conges= data;
    });
  }
}
