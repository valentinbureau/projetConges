import { CongesService } from './../Services/conges.service';
import { Conge } from './../model/conge';
import { Component, OnInit, NgModule, ViewChild } from '@angular/core';
import { EnumCongé } from '../model/enum-congé.enum';
import { ActivatedRoute, Router } from '@angular/router';
import { DateAdapter } from '@angular/material/core';
import { MatDatepicker } from '@angular/material/datepicker';
import { DatePipe } from '@angular/common';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-demande',
  templateUrl: './demande.component.html',
  styleUrls: ['./demande.component.css'],
})
export class DemandeComponent implements OnInit {
  @ViewChild(MatDatepicker) datepicker: MatDatepicker<Date>;
  conge: Conge = new Conge();
  public dateNow = new Date(Date.now()).toLocaleDateString();
  keys = Object.keys;
  typeConges = EnumCongé;
  date = new FormControl(new Date());

  constructor(
    private activatedRoute: ActivatedRoute,
    private CongesService: CongesService,
    private router: Router,
    private dateAdapter: DateAdapter<any>,
    private datePipe: DatePipe
  ) {
    this.dateAdapter.setLocale('fr-FR'); //dd/MM/yyyy
  }

  ngOnInit(): void {}

  save() {
    if (this.conge.dateFin.getTime() < this.conge.dateDebut.getTime()) {
      alert("La date de fin n'est pas valide");
    } else if (this.conge.dateDebut.getTime() < Date.now()) {
      alert("La date de début des congés n'est pas valide");
    } else if (
      this.conge.type == null ||
      this.conge.dateDebut == null ||
      this.conge.dateFin == null
    ) {
      alert(
        'Veuillez remplir tous les champs pour pouvoir soumettre votre demande'
      );
    } else {
      let dateDebut = this.conge.dateDebut.toLocaleDateString();
      console.log(this.conge.dateDebut);
      console.log(this.conge.dateFin);
      this.conge.dateDemande = new Date(Date.now());
      console.log(Date.parse(dateDebut));
      console.log(this.conge.dateDemande.getDate);
      console.log(typeof this.conge.dateDemande.toLocaleDateString);
      console.log(JSON.stringify(this.conge));
      console.log('---------------------------');
      console.log(typeof this.conge.dateDebut);
      this.CongesService.insert(this.conge).subscribe((data) => {
        this.router.navigate(['conge/employe/:login']);
      });
    }
  }
}
