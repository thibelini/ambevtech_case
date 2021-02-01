import { NgxSmartModalModule, NgxSmartModalService } from 'ngx-smart-modal';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgSelectModule } from '@ng-select/ng-select';
import { ModalModule } from 'ngx-bootstrap/modal';
import { ToastrModule } from 'ngx-toastr';
import { CidadeBuscarComponent } from './cidade-buscar/cidade-buscar.component';
import { CidadeListarComponent } from './cidade-listar/cidade-listar.component';
import { CidadeRoutingModule } from './cidade-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { PaginationModule } from 'ngx-bootstrap/pagination';


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
    CidadeBuscarComponent 
  ]
})
export class CidadeModule { }