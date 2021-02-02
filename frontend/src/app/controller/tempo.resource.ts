import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cidade } from '../model/Cidade';
import { DadosTempoDTO } from '../model/DadosTempoDTO';

@Injectable({
    providedIn: 'root'
})
export class TempoResource {

    constructor(private http: HttpClient) {}

    public dadosTempoCidade(latitude: string, longitude: string): Observable<DadosTempoDTO> {
        return this.http.get<any>('http://localhost:8080/tempo/dados', 
            {
                 params: new HttpParams()
                    .set('latitude', latitude)
                    .set('longitude', longitude) 
            });
    }

   
}