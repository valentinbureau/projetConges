import { EnumRole } from './enum-role.enum';


export class Login {
  private _id: number;
  private _login: string;
  private _password: string;
  private _role:EnumRole;


  constructor(login?: string, password?: string) {
    this._login = login;
    this._password = password;
  }

  public get id(): number {
    return this._id;
  }

  public set id(value: number) {
    this._id = value;
  }

  public get login(): string {
    return this._login;
  }

  public set login(value: string) {
    this._login = value;
  }

  public get password(): string {
    return this._password;
  }

  public set password(value: string) {
    this._password = value;
  }

  public get role(): EnumRole {
    return this._role;
  }

  public set role(value: EnumRole) {
    this._role = value;
  }


}
