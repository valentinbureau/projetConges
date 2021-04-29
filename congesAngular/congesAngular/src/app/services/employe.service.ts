import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeService {

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

  public creationEmploye(nom: string, prenom: string, service_id: number, login_id : number): Observable<void> {
    this.initHeader();
    const EmployeFormat = {
      nom : nom,
      prenom: prenom,
      service: { id: service_id },
      login: { id: login_id }
    }
    return this.http.post<void>(
      'http://localhost:8080/vacances/api/employe',
      EmployeFormat,
      { headers: this.httpHeaders },
    );
  }
}
