import { EnumRole } from "./enum-role.enum";
import { Login } from "./login";

export class RoleLogin {



  constructor(
    private _id?: number,
    private _login?: Login,
    private _role?:EnumRole,
  ) {
  }

  public get id(): number {
    return this._id;
  }

  public set id(value: number) {
    this._id = value;
  }

  public get login(): Login {
    return this._login;
  }

  public set login(value: Login) {
    this._login = value;
  }

  public get role(): EnumRole {
    return this._role;
  }

  public set role(value: EnumRole) {
    this._role = value;
  }


}
