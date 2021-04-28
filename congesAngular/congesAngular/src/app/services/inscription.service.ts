import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InscriptionService {

  constructor(private http: HttpClient) { }

  public inscription(login: string, password: string): Observable<void> {
    return this.http.post<void>(
      'http://localhost:8080/vacances/api/auth',
      { login : login, password: password }
    );
  }

  public checkLogin(login:string): Observable<boolean> {
    return this.http.get<boolean>(`http://localhost:8080/vacances/api/auth/${login}`)
  }
}
