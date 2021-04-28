import { TypeCongeMapping } from './../model/enum-congé.enum';
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


  constructor() { }

  ngOnInit(): void {
  }

public TypeCongeMapping = TypeCongeMapping;
public typeConges= Object.values(EnumCongé);

}
