import { EnumStatus } from './../../model/enum-status.enum';
import { Conge } from './../../model/conge';
import { Observable } from 'rxjs';
import { Login } from './../../model/login';
import { CongesService } from './../../services/conges.service';
import { Employe } from './../../model/employe';
import { ServiceEmployesService } from './../../services/service-employes.service';
import { Component, OnInit } from '@angular/core';
import { Service } from 'src/app/model/service';


@Component({
  selector: 'app-listconges-manager',
  templateUrl: './listconges-manager.component.html',
  styleUrls: ['./listconges-manager.component.css']
})
export class ListcongesManagerComponent implements OnInit {
  service: Service;
  conge: Conge[];
  listConge : Conge[];
  conges : Conge = new Conge();
  congePresent: string;
  employe: Employe;
  idArrayConge : number[];
  idArrayDemande : number[];
  login: string;
  nom : string;

  constructor(private serviceEmployesService : ServiceEmployesService,
    private congesService : CongesService,) { }

  ngOnInit(): void {
    this.employe = JSON.parse(localStorage.getItem("employe"));
    this.service = this.employe.service;
    this.nom=this.employe.nom;
    this.serviceEmployesService.getService(this.service.id).subscribe((data) => {
      this.service = data;
      this.login = localStorage.getItem('login');


    });
    this.idArrayConge = [];
    this.idArrayDemande = [];
  }

  public clickConge(id: number) {
    if (this.idArrayConge.includes(id)) {
      this.idArrayConge = this.idArrayConge.filter( number => id != number );
    } else {
      if (this.idArrayDemande.includes(id)){
        this.idArrayDemande = this.idArrayDemande.filter( number => id != number );
      }
      this.idArrayConge.push(id);
    }}

  private list(e:Employe) {
    this.congesService.findAllbyNomEmploye(e.nom).subscribe((data) => {
      this.listConge= data;
      });

  }

  private getService(){
    console.log("hello");

    console.log(localStorage.getItem('auth'))
    console.log(this.service);
  }

  public clickDemande(id: number) {
    if (this.idArrayDemande.includes(id)) {
      this.idArrayDemande = this.idArrayDemande.filter( number => id != number );
    } else {
      if (this.idArrayConge.includes(id)){
        this.idArrayConge = this.idArrayConge.filter( number => id != number );
      }
      this.idArrayDemande.push(id);
    }

      this.congePresent === null;
    }


public Accept(c:Conge){
   c.statut=EnumStatus.Acceptée;
   this.congesService.update(c).subscribe((res) => {
});}

public Decline(c:Conge){
  c.statut=EnumStatus.Refusée;
  this.congesService.update(c).subscribe((res) => {
});


}

  }



