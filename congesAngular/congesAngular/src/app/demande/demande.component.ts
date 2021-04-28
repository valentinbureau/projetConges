
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
      this.CongesService.insert(this.conge).subscribe((data) => {
        this.router.navigate(['/conge']);
      });
    }
  }

