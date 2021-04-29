import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InscriptionService {

  private httpHeaders: HttpHeaders;

  constructor(private http: HttpClient) {
    this.initHeader;
  }

  private initHeader() {
    this.httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${localStorage.getItem('auth')}`,
    });
  }


  public inscription(login: string, password: string): Observable<void> {
    console.log(login);
    console.log(password);
    const LoginFormat = {
      login : login,
      password: password,
    }
    return this.http.post<void>(
      'http://localhost:8080/vacances/api/auth',
      LoginFormat,
      { headers: this.httpHeaders },
    );
  }

  public checkLogin(login:string): Observable<boolean> {
    return this.http.get<boolean>(`http://localhost:8080/vacances/api/auth/inscription/${login}`)
  }
}
