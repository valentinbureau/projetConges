import { ServiceEmployesService } from './../../services/service-employes.service';
import { InscriptionService } from './../../services/inscription.service';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { EmployeService } from './../../services/employe.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Service } from 'src/app/model/service';
import { Employe } from 'src/app/model/employe';

@Component({
  selector: 'app-createemploye',
  templateUrl: './createemploye.component.html',
  styleUrls: ['./createemploye.component.css']
})
export class CreateemployeComponent implements OnInit {
  nomCtrl: FormControl;
  prenomCtrl: FormControl;
  ServiceCtrl: FormControl;
  form: FormGroup;
  idLogin: number;
  services: Service[];


  constructor(private EmployeService: EmployeService, private route: Router, private fb: FormBuilder, private InscriptionService: InscriptionService, private ServiceEmployesService: ServiceEmployesService) {
    this.nomCtrl = this.fb.control(
      '',
      [Validators.required],
    );
    this.prenomCtrl = this.fb.control(
      '',
      [Validators.required],

    );
    this.ServiceCtrl = this.fb.control(
      '',
      [Validators.required],

    );
    this.form = this.fb.group({
      nom: this.nomCtrl,
      prenom: this.prenomCtrl,
      service: this.ServiceCtrl,
    });
  }

  ngOnInit(): void {
    this.ServiceEmployesService.getAllService().subscribe(data => {
      this.services = data
    }
    );
  }


  send(){
    console.log('je passe ici');
    console.log(this.ServiceCtrl.value);
    this.InscriptionService.idLogin(localStorage.getItem("loginTemporaire")).subscribe(data => {
      this.idLogin = data.id
    })
    this.EmployeService.creationEmploye(this.nomCtrl.value, this.prenomCtrl.value, this.ServiceCtrl.value, this.idLogin).subscribe((res) => {
      this.route.navigate(['/conge/manager']);
    })
  }
}
