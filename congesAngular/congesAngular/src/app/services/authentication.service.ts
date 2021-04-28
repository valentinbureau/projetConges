import { Observable } from 'rxjs';
import { Login } from 'src/app/model/login';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  public authentification(login: Login ) {}

  public getAuthApi(login: Login): Observable<void> {
    const texte: string = `${login.login}:${login.password}`;
    const headers: HttpHeaders = new HttpHeaders({
      'Content-Type':'application/json',
      authorization: `Basic ${btoa(texte)}`
    })
    return this.http.get<void>('http://127.0.0.1:8080/vacances/api/auth/login', { headers: headers });
  }
}
