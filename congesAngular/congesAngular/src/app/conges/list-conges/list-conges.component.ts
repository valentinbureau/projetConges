import { Component, OnInit } from '@angular/core';
import { Conge } from 'src/app/model/conge';
import { EnumStatus } from 'src/app/model/enum-status.enum';
import { CongesService } from 'src/app/Services/conges.service';

@Component({
  selector: 'app-list-conges',
  templateUrl: './list-conges.component.html',
  styleUrls: ['./list-conges.component.css']
})
export class ListCongesComponent implements OnInit {
  conge : Conge = new Conge();
  conges: Conge[];
  login: string;


  keys = Object.keys;
  statutConges = EnumStatus;
  constructor(
    private congeService: CongesService,
  ) { }

  ngOnInit(): void {
    this.login = localStorage.getItem('login');
    this.list();
  }

  private list() {
    console.log(this.login);
    this.congeService.findAllbyLogin(this.login).subscribe((data) => {
      this.conges= data;
    });
  }
}
