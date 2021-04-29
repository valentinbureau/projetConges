import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RoleLogin } from '../model/role-login';

@Injectable({
  providedIn: 'root'
})
export class RoleLoginService {

  private static URL: string = 'http://127.0.0.1:8080/vacances/api/rolelogin';
  private httpHeaders: HttpHeaders;

  constructor(private http: HttpClient) {
    this.initHeader();
  }

  private initHeader() {
    this.httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Basic ${localStorage.getItem('auth')}`,
    });
  }

  public getRoleLoginbyLogin(login: string): Observable<RoleLogin> {
    this.initHeader();
    return this.http.get<RoleLogin>(RoleLoginService.URL + '/' + login, {
      headers: this.httpHeaders,
    });
  }
}
