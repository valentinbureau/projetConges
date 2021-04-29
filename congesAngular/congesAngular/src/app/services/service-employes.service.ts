import { Service } from './../model/service';
import { Employe } from './../model/employe';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServiceEmployesService {

  private static URL : string = "http://127.0.0.1:8080/vacances/api/service";
  private httpHeaders: HttpHeaders;

  constructor(private http: HttpClient) {
    this.initHeader();
  }

  private initHeader(){
    this.httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${localStorage.getItem('auth')}`,
    });
  }

  public getEmployes(id: number): Observable<Employe[]>{
    this.initHeader();
    return this.http.get<Employe[]>(ServiceEmployesService.URL + '/' + id, { headers: this.httpHeaders, });
  };

  public getService(id: number): Observable<Service>{
    this.initHeader();
    return this.http.get<Service>(ServiceEmployesService.URL + '/' + id, { headers: this.httpHeaders, });
  };
}
