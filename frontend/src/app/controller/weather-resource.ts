import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";


@Injectable({
    providedIn: 'root'
})
export class WeatherResource {

    constructor(private http: HttpClient) {}

    public procurarCidade(nomeCidade: string): Observable<Array<any>> {
        return this.http.get<Array<any>>('http://localhost:8080/tempo',{ params: new HttpParams().set('nomeCidade', nomeCidade) });
    }

}