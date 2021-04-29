import { Conge } from './conge';
import { Service } from "./service";

export class Employe {

public constructor(
  private _id?:number,
  private _nom?:string,
  private _prenom?:string,
  private _service?:Service,
  private _conges?: Conge[],

)
{}

  public get id(): number {
    return this._id;
  }

  public set id(value: number) {
    this._id = value;
  }

  public get prenom(): string {
    return this._prenom;
  }

  public set prenom(value: string) {
    this._prenom = value;
  }
  public get nom(): string {
    return this._nom;
  }

  public set nom(value: string) {
    this._nom = value;
  }

  public get service(): Service {
    return this._service;
  }

  public set service(service: Service) {
    this._service = service;
  }

  public get conges(): Conge[] {
    return this._conges;
  }

  public set conges(conges: Conge[]) {
    this._conges = conges;
  }
}


