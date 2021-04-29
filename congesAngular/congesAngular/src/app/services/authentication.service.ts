import { Observable } from 'rxjs';
import { Login } from 'src/app/model/login';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Employe } from '../model/employe';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
private HttpHeaders: HttpHeaders;

  constructor(private http: HttpClient) { }

  public authentification(login: Login ) {}

  public getAuthApi(login: Login): Observable<void> {
    const texte: string = `${login.login}:${login.password}`;
    this.HttpHeaders = new HttpHeaders({
      'Content-Type':'application/json',
      'authorization': `Basic ${btoa(texte)}`
    })
    return this.http.get<void>('http://127.0.0.1:8080/vacances/api/auth/*', { headers: this.HttpHeaders });
  }

  public getEmploye(login: Login): Observable<Employe>{
    return this.http.get<Employe>(`http://127.0.0.1:8080/vacances/api/employe/login/${login.login}`, { headers: this.HttpHeaders });
  }
}
