import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cidade } from '../model/Cidade';

@Injectable({
    providedIn: 'root'
})
export class CidadeResource {

    constructor(private http: HttpClient) {}

    public filtrar(filtro): Observable<Array<Cidade>> {
        return this.http.post<Array<Cidade>>('http://localhost:8080/cidades/filtrar', filtro);
    }

    public salvar(cidade: any): Observable<any> {
        return this.http.post<any>('http://localhost:8080/cidades', cidade);
    }

   
}