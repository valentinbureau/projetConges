import { Employe } from './../../model/employe';
import { ServiceEmployesService } from './../../services/service-employes.service';
import { Component, OnInit } from '@angular/core';
import { Service } from 'src/app/model/service';
import { Conge } from 'src/app/model/conge';

@Component({
  selector: 'app-listconges-manager',
  templateUrl: './listconges-manager.component.html',
  styleUrls: ['./listconges-manager.component.css']
})
export class ListcongesManagerComponent implements OnInit {
  service: Service;
  conge: Conge[];
  congePresent: string;
  employe: Employe;
  idArrayConge : number[];
  idArrayDemande : number[];

  constructor(private serviceEmployesService : ServiceEmployesService) { }

  ngOnInit(): void {
    this.employe = JSON.parse(localStorage.getItem("employe"));
    this.service = this.employe.service;
    this.serviceEmployesService.getService(this.service.id).subscribe((data) => {
      this.service = data;
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
    }
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
  }
}
