  import { EnumStatus } from './enum-status.enum';
  import { EnumCongé } from './enum-congé.enum';

  export class Conge {

  public constructor(
    private _id?:number,
    private _dateDemande?:Date,
    private _type?:EnumCongé,
    private _dateDebut?:Date,
    private _dateFin?:Date,
    private _motif?:string,
    private _statut?:EnumStatus,
    private _raisonRefus?:Text,
  ){}

  public get id(): number {
    return this._id;
  }

  public set id(value: number) {
    this._id = value;
  }

  public get dateDemande(): Date {
    return this._dateDemande;
  }

  public set dateDemande(value: Date) {
    this._dateDemande = value;
  }

  public get type(): EnumCongé {
    return this._type;
  }

  public set type(value: EnumCongé) {
    this._type = value;
  }

  public get dateDebut(): Date {
    return this._dateDebut;
  }

  public set dateDebut(value: Date) {
    this._dateDebut = value;
  }

  public get dateFin(): Date {
    return this._dateFin;
  }

  public set dateFin(value: Date) {
    this._dateFin = value;
  }

  public get motif(): string {
    return this._motif;
  }

  public set motif(value: string) {
    this._motif=value;
  }

  public get statut(): EnumStatus {
    return this._statut;
  }

  public set statut(value: EnumStatus) {
    this._statut=value;
  }

  public get raisonRefus(): Text {
    return this._raisonRefus;
  }

  public set raisonRefus(value: Text) {
    this._raisonRefus=value;
  }
  }

