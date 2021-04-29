import { Service } from './../model/service';
import { Employe } from './../model/employe';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ServiceEmployesService {
  private static URL: string = 'http://127.0.0.1:8080/vacances/api/service';
  private HttpHeaders: HttpHeaders;

  constructor(private http: HttpHeaders) {
    this.initHeader();
  }

  private initHeader() {
    this.HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${localStorage.getItem('auth')}`,
    });
  }

  public getEmployes(): Observable<Employe[]> {
    this.initHeader();
    return this.http.get<Service>(ServiceEmployesService.URL, {
      headers: this.HttpHeaders,
    });
  }
}
