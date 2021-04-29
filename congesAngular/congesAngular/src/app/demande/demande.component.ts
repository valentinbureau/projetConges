import { CongesService } from './../Services/conges.service';
import { Conge } from './../model/conge';
import { Component, OnInit, NgModule } from '@angular/core';
import { EnumCongé } from '../model/enum-congé.enum';
import { ActivatedRoute, Router } from '@angular/router';



@Component({
  selector: 'app-demande',
  templateUrl: './demande.component.html',
  styleUrls: ['./demande.component.css']
})
export class DemandeComponent implements OnInit {
conge : Conge = new Conge();
public dateNow=Date.now();
keys = Object.keys;
typeConges = EnumCongé;


  constructor(
    private activatedRoute : ActivatedRoute,
    private CongesService : CongesService,
    private router : Router, ) {  };

  ngOnInit(): void {
  }

  save() {
    if(this.conge.dateFin.getTime()<this.conge.dateDebut.getTime()){
      alert("La date de fin n'est pas valide")}
      else if(this.conge.dateDebut.getTime()<Date.now()){
      alert("La date de début des congés n'est pas valide")}
      else if(this.conge.type==null||this.conge.dateDebut==null||this.conge.dateFin==null){
      alert("Veuillez remplir tous les champs pour pouvoir soumettre votre demande")}
  else{
      this.CongesService.insert(this.conge).subscribe((data) => {
        this.router.navigate(['/conge']);
      });
    }
  }
}
