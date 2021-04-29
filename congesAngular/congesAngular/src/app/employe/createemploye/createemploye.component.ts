import { FormBuilder } from '@angular/forms';
import { EmployeService } from './../../services/employe.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-createemploye',
  templateUrl: './createemploye.component.html',
  styleUrls: ['./createemploye.component.css']
})
export class CreateemployeComponent implements OnInit {
  nomCtrl: FormBuilder;
  prenomCtrl: FormBuilder;
  ServiceCtrl: FormBuilder;

  constructor(private EmployeService: EmployeService, private route: Router, private fb: FormBuilder) { }

  ngOnInit(): void {
  }


  //send(){
  //  this.EmployeService.creationEmploye(this.nomCtrl.value, this.prenomCtrl.value, this.ServiceCtrl.value)
  //}
}
