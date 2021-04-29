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
  congesFiltres: Conge[] = [];
  login: string;
  filter = { acceptee: true, enCours: false, refusee: false }

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
      this.conges=data;
      this.congesFiltres=this.conges;
    });
  }

  filterChange() {

    this.congesFiltres = this.conges.filter(c => {
      return(( c.statut === EnumStatus.Acceptée && this.filter.acceptee)
      || (c.statut === EnumStatus['En cours de traitement'] && this.filter.enCours)
      || (c.statut === EnumStatus.Refusée && this.filter.refusee))


        /*console.log('enumAcceptee',EnumStatus['En cours de traitement']);
        console.log('statut',c.statut);
        console.log(c);*/
    });
    console.log("c'est filtré?", this.congesFiltres);
    console.log(this.filter.acceptee)
  }
  /*add(statutconge: string) {
    this.statutDemande.push(statutconge);
    this.ngOnInit();
  }*/

}
