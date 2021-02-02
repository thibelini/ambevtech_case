import { TempoResource } from './../controller/tempo.resource';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CidadeResource } from '../controller/cidade-resource';
import { Cidade } from '../model/Cidade';
import { DadosTempoDTO } from '../model/DadosTempoDTO';

@Injectable({
  providedIn: 'root'
})
export class TempoService {

constructor(private resource: TempoResource) { }

  public dadosTempoCidade(latitude: string, longitude: string) : Observable<DadosTempoDTO> {
    return this.resource.dadosTempoCidade(latitude, longitude);
  }

}
