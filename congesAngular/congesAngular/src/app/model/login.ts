import { EnumRole } from './enum-role.enum';
import { Employe } from './employe';

export class Login {
  private _login: string;
  private _motDePass: string;
  private _role:EnumRole;


  constructor(login?: string, motDePass?: string) {
    this._login = login;
    this._motDePass = motDePass;
  }


  public get login(): string {
    return this._login;
  }

  public set login(value: string) {
    this._login = value;
  }

  public get motDePass(): string {
    return this._motDePass;
  }

  public set motDePass(value: string) {
    this._motDePass = value;
  }

  public get role(): EnumRole {
    return this._role;
  }

  public set role(value: EnumRole) {
    this._role = value;
  }


}
