import { Employe } from './employe';
export class Service {

  public constructor(
    private _id?:number,
    private _nom?:string,
    private _manager?:Employe,
  ){}

  public get id(): number {
    return this._id;
  }

  public set id(value: number) {
    this._id = value;
  }

  public get nom(): string {
    return this._nom;
  }

  public set nom(value: string) {
    this._nom= value;
  }

  public get manager(): Employe {
    return this._manager;
  }

  public set manager(value: Employe) {
    this._manager= value;
  }
}
