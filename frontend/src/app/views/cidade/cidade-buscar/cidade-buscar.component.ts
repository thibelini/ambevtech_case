import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { concat, Observable, of, Subject } from 'rxjs';
import { catchError, debounceTime, distinctUntilChanged, filter, map, switchMap, tap } from 'rxjs/operators';
import { CidadeService } from '../../../service/cidade.service';
import { WeatherService } from '../../../service/weather.service';

@Component({
  selector: 'app-cidade-buscar',
  templateUrl: './cidade-buscar.component.html',
  styleUrls: ['./cidade-buscar.component.scss']
})
export class CidadeBuscarComponent implements OnInit {
  
  @Output() onSalvar = new EventEmitter();
  
  public cidades: Observable<any>;
  public cidadesLoading = false;
  public cidadesInput = new Subject<any>();
  public selectedCidade: any;
  public minLengthTerm = 4;
  public form: FormGroup;

  constructor(
      private weatherService: WeatherService, 
      private toastr: ToastrService,
      private formBuilder: FormBuilder,
      private cidadeService: CidadeService) {

        this.form = formBuilder.group({
          nome: [null, Validators.compose([Validators.required])],
        });

      }

  ngOnInit() {
    this.loadCidades();
  }

  loadCidades() {
    this.cidades = concat(
      of([]),
      this.cidadesInput.pipe(
        filter(res => {
          return res !== null && res.length >= this.minLengthTerm
        }),
        distinctUntilChanged(),
        debounceTime(300),
        tap(() => this.cidadesLoading = true),
        switchMap(term => {
          return this.getCidade(term).pipe(
            catchError(() => of([])), // empty list on error
            tap(() => this.cidadesLoading = false)
          )
        })
      )
    );

  }

  trackByFn(item: any) {
    return item;
  }

  getCidade(term: string): Observable<any> {
    return this.weatherService.procurarCidade(term)
      .pipe(map((resp: any) => {
        if (resp.Error) {
          this.toastr.error('Atenção', resp.Error);
        } else {
          return resp;
        }
      }),
      );
  }

  public salvarCidade(){
    this.onSalvar.emit(this.selectedCidade);

  }

}
