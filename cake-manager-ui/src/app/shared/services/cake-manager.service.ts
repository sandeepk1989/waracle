import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Cake } from '../models/cake.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class CakeManagerService {
  constructor(private httpClient: HttpClient) {}

  getAllCakes(): Observable<any> {
    return this.httpClient.get(environment.backendUrl);
  }

  getAllCakesJson(): Observable<any> {
    return this.httpClient.get(environment.backendUrl + 'cakes', httpOptions);
  }

  addNewCake(cake: Cake): Observable<any> {
    const headers = new HttpHeaders().set(
      'Content-Type',
      'application/json; charset=utf-8'
    );
    return this.httpClient.post<Cake>(
      environment.backendUrl + 'cakes',
      JSON.stringify(cake),
      { headers: headers }
    );
  }
}
