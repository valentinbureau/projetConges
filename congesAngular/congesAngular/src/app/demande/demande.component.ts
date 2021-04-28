import { Conge } from './../model/conge';
import { Component, OnInit, NgModule } from '@angular/core';
import { EnumCongé } from '../model/enum-congé.enum';

@Component({
  selector: 'app-demande',
  templateUrl: './demande.component.html',
  styleUrls: ['./demande.component.css']
})
export class DemandeComponent implements OnInit {
conge : Conge = new Conge();

  keys = Object.keys;
  typeConges = EnumCongé;
  constructor() { }

  ngOnInit(): void {
  }

//public typeConges= Object.values(EnumCongé);

}
