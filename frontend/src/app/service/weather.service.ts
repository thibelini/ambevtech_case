import { WeatherResource } from './../controller/weather-resource';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

constructor(private resource: WeatherResource) { }

  public procurarCidade(nomeCidade: string): Observable<Array<any>> {
    return this.resource.procurarCidade(nomeCidade);
  }

}
