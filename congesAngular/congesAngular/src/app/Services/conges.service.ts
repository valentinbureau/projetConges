import { EnumStatus } from './../model/enum-status.enum';
import { Conge } from './../model/conge';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EnumCongé } from '../model/enum-congé.enum';



@Injectable({providedIn: 'root'})
export class ServiceNameService {
  constructor(private httpClient: HttpClient) { }

}

@Injectable({
  providedIn: 'root'
})
export class CongesService {

  private static URL = 'http://127.0.0.1:8080/vacances/api/conge';
  private httpHeaders : HttpHeaders;
  keys = Object.keys;
  statut = EnumStatus;

  constructor(private http: HttpClient,
    )
    {
    this.initHeader();}


  private initHeader() {
    this.httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Basic ${localStorage.getItem('auth')}`,
    });
  }

  public getConges(): Observable<Conge[]> {
    this.initHeader();
    return this.http.get<Conge[]>(CongesService.URL, {
      headers: this.httpHeaders,
    });
  }

  public delete(id: number): Observable<void> {
    this.initHeader();
    return this.http.delete<void>(CongesService.URL + '/' + id, {
      headers: this.httpHeaders,
    });
  }

  public getConge(id: number): Observable<Conge> {
    this.initHeader();
    return this.http.get<Conge>(CongesService.URL + '/' + id, {
      headers: this.httpHeaders,
    });
  }

  public update(conge: Conge): Observable<Conge> {
    this.initHeader();
    return this.http.put<Conge>(
      CongesService.URL + '/' + conge.id,
      conge,
      {
        headers: this.httpHeaders,
      }
    );
  }

  public insert(conge: Conge): Observable<Conge> {
    this.initHeader();
    const congeFormate = {
      typeConge : conge.type,
      dateDebut : conge.dateDebut,
      dateFin : conge.dateFin,
      motif : conge.motif,
      dateDemande : conge.dateDemande,
      statut : EnumStatus.EN_COURS_DE_TRAITEMENT,
    };
    console.log(conge.dateDebut);
    return this.http.post<Conge>(CongesService.URL, congeFormate, {
      headers: this.httpHeaders,
    });
  }

  public findAllbyLogin(login: string): Observable<Conge[]> {
    this.initHeader();
    return this.http.get<Conge[]>(CongesService.URL + '/employe/' + login, {
      headers: this.httpHeaders,
    });
  }
}
