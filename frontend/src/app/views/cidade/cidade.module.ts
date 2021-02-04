import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgSelectModule } from '@ng-select/ng-select';
import { PaginationModule } from 'ngx-bootstrap/pagination';
import { NgxSmartModalModule, NgxSmartModalService } from 'ngx-smart-modal';
import { OverlayComponent } from './../componentes/overlay/overlay.component';
import { CidadeBuscarComponent } from './cidade-buscar/cidade-buscar.component';
import { CidadeListarComponent } from './cidade-listar/cidade-listar.component';
import { CidadeRoutingModule } from './cidade-routing.module';
import { TempoComponent } from './tempo/tempo.component';


@NgModule({
  imports: [
    FormsModule,
    CommonModule,
    CidadeRoutingModule,
    NgxSmartModalModule.forRoot(),
    NgSelectModule,
    FormsModule, 
    ReactiveFormsModule,
    PaginationModule.forRoot()
  ],
  providers: [ NgxSmartModalService ],
  declarations: [ 
    CidadeListarComponent, 
    CidadeBuscarComponent,
    OverlayComponent,
    TempoComponent
  ]
})
export class CidadeModule { }