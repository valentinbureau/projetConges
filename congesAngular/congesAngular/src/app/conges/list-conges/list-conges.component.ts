import { Component, HostListener, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Conge } from 'src/app/model/conge';
import { EnumStatus } from 'src/app/model/enum-status.enum';
import { CongesService } from 'src/app/Services/conges.service';

@Component({
  selector: 'app-list-conges',
  templateUrl: './list-conges.component.html',
  styleUrls: ['./list-conges.component.css']
})
export class ListCongesComponent implements OnInit {
  //conge : Conge = new Conge();
  conges: Conge[];
  login: string;


  conge = new FormControl();

  keys = Object.keys;
  statutConges: string[] = [EnumStatus.Acceptée,EnumStatus['En cours de traitement'],EnumStatus.Refusée];
  statutDemande: string[];
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
      /*data.filter(data=> {
        console.log(this.statutConges);
        if (this.statutConges.includes(data.statut)){
          //console.log(data);
        }
      });*/
      this.conges=data;
    });
  }

  /*add(statutconge: string) {
    this.statutDemande.push(statutconge);
    this.ngOnInit();
  }*/

}
