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

  constructor(private serviceEmployesService : ServiceEmployesService) { }

  ngOnInit(): void {
    this.employe = JSON.parse(localStorage.getItem("employe"));
    this.service = this.employe.service;
    this.serviceEmployesService.getService(this.service.id).subscribe((data) => {
      this.service = data;
    });
  }

  private getService(){
    console.log("hello");

    console.log(localStorage.getItem('auth'))
    console.log(this.service);
  }

  private voirConges(id: number){
    if (this.congePresent === "ok"){
      this.conge = [new Conge()];
    } else {
      this.congePresent === null;
    }
  }
}
