import { EnumStatus } from './../../model/enum-status.enum';
import { Conge } from './../../model/conge';
import { Observable } from 'rxjs';
import { Login } from './../../model/login';
import { CongesService } from './../../services/conges.service';
import { Employe } from './../../model/employe';
import { ServiceEmployesService } from './../../services/service-employes.service';
import { Component, OnInit, Inject } from '@angular/core';
import { Service } from 'src/app/model/service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';


export interface DialogData {

  refus: string;
}

@Component({
  selector: 'app-listconges-manager',
  templateUrl: './listconges-manager.component.html',
  styleUrls: ['./listconges-manager.component.css']
})
export class ListcongesManagerComponent implements OnInit {
  service: Service;
  conge: Conge[];
  listConge : Conge[];
  conges : Conge = new Conge();
  congePresent: string;
  employe: Employe;
  idArrayConge : number[];
  idArrayDemande : number[];
  login: string;
  nom : string;

  constructor(private serviceEmployesService : ServiceEmployesService,
    private congesService : CongesService,) { }

  ngOnInit(): void {
    this.employe = JSON.parse(localStorage.getItem("employe"));
    this.service = this.employe.service;
    this.serviceEmployesService.getService(this.service.id).subscribe((data) => {
      this.service = data;
      this.login = localStorage.getItem('login');


    });
    this.idArrayConge = [];
    this.idArrayDemande = [];
  }

  public clickConge(id: number) {
    if (this.idArrayConge.includes(id)) {
      this.idArrayConge = this.idArrayConge.filter( number => id != number );
    } else {
      if (this.idArrayDemande.includes(id)){
        this.idArrayDemande = this.idArrayDemande.filter( number => id != number );
      }
      this.idArrayConge.push(id);
    }}



  private getService(){
    console.log("hello");

    console.log(localStorage.getItem('auth'))
    console.log(this.service);
  }

  public clickDemande(id: number) {
    if (this.idArrayDemande.includes(id)) {
      this.idArrayDemande = this.idArrayDemande.filter( number => id != number );
    } else {
      if (this.idArrayConge.includes(id)){
        this.idArrayConge = this.idArrayConge.filter( number => id != number );
      }
      this.idArrayDemande.push(id);
    }

      this.congePresent === null;
    }


public Accept(conge:Conge){
   conge.statut=EnumStatus.Acceptée;
   this.congesService.update(conge).subscribe((res) => {
  });
}

public Decline(conge:Conge){
  //openDialog();
  conge.statut=EnumStatus.Refusée;
  this.congesService.update(conge).subscribe((res) => {
  });
}



}


/**
 * @title Dialog Overview
 */
@Component({
  selector: 'dialog-overview-example',
  templateUrl: 'dialog-overview-example.html',
})
export class DialogOverviewExample {


  refus: string;

  constructor(public dialog: MatDialog) {}

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '250px',
      data: {refus: this.refus}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.refus = result;
    });
  }

}

@Component({
  selector: 'dialog-overview-example-dialog',
  templateUrl: 'dialog-overview-example-dialog.html',
})
export class DialogOverviewExampleDialog {

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}



