import { CongesService } from './../Services/conges.service';
import { Conge } from './../model/conge';
import { Component, OnInit, NgModule } from '@angular/core';
import { EnumCongé } from '../model/enum-congé.enum';
import { ActivatedRoute, Router } from '@angular/router';
import { DateAdapter } from '@angular/material/core';

@Component({
  selector: 'app-demande',
  templateUrl: './demande.component.html',
  styleUrls: ['./demande.component.css'],
})
export class DemandeComponent implements OnInit {
  conge: Conge = new Conge();
  public dateNow = Date.now();
  keys = Object.keys;
  typeConges = EnumCongé;

  constructor(
    private activatedRoute: ActivatedRoute,
    private CongesService: CongesService,
    private router: Router,
    private dateAdapter: DateAdapter<Date>
  ) {
    this.dateAdapter.setLocale('en-GB'); //dd/MM/yyyy
  }

  ngOnInit(): void {}

  save() {
    this.dateAdapter.setLocale('en-GB'); //dd/MM/yyyy
    console.log('Date debut : ' + this.conge.dateDebut.toLocaleDateString());
    // const dateDebut = new Date(this.conge.dateDebut.toLocaleDateString());
    const dateDebut = new Date(this.conge.dateDebut);
    const dateFin = new Date(this.conge.dateFin.toLocaleDateString());
    console.log('apres conv : ' + dateDebut);
    //this.conge.dateDebut = dateDebut;
    // this.conge.dateFin = dateFin;
    // this.conge.dateDemande = dateDemande;

    this.CongesService.insert(this.conge).subscribe((data) => {
      this.router.navigate(['/conge']);
    });
  }
}
