import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CidadeResource } from '../controller/cidade-resource';
import { Cidade } from '../model/cidade';

@Injectable({
  providedIn: 'root'
})
export class CidadeService {

constructor(private resource: CidadeResource) { }

  public filtrar(filtro): Observable<Array<Cidade>> {
    if (!filtro) {
        filtro.page = 0;
        filtro.size = 7;
    }
    return this.resource.filtrar(filtro);
  }

  public salvar(cidade: any) : Observable<any> {
    console.log('service');
    return this.resource.salvar(cidade); 
  }
  

}




