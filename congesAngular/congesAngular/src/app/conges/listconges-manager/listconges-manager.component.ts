import { Employe } from './../../model/employe';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-listconges-manager',
  templateUrl: './listconges-manager.component.html',
  styleUrls: ['./listconges-manager.component.css']
})
export class ListcongesManagerComponent implements OnInit {
  employe : Employe;
  constructor() { }

  ngOnInit(): void {
  }

}
