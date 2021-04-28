export class Employe {

public constructor(
  private _id?:number,
  private _nom?:string,
  private _prenom?:string,
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

}


