import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgxSmartModalService } from 'ngx-smart-modal';
import { Subject } from 'rxjs';
import { Cidade } from '../../../model/Cidade';
import { CidadeDTO } from '../../../model/CidadeDTO';
import { FiltroDTO } from './../../../model/FiltroDTO';
import { TempoObjDTO } from './../../../model/TempoObjDTO';
import { CidadeService } from './../../../service/cidade.service';
import { MensagemService } from './../../../service/mensagem.service';
import { CidadeBuscarComponent } from './../cidade-buscar/cidade-buscar.component';


@Component({
  selector: 'app-cidade-listar',
  templateUrl: './cidade-listar.component.html',
  styleUrls: ['./cidade-listar.component.scss']
})
export class CidadeListarComponent implements OnInit, AfterViewInit {

  @ViewChild('myModal') modal: CidadeBuscarComponent;

  public cidades: Array<Cidade> = [];
  public mensagem: string;
  public cidadesBusca = []
  public subject: Subject<any> = new Subject();
  public FiltroDTO: FiltroDTO = new FiltroDTO();
  public paginaSelecionada = 1;
  public totalElementos = 0;
  public formFiltro: FormGroup;
  public abrirOverlayTempo = false;
  public tempoObjDTO: TempoObjDTO;


  constructor(
    private cidadeService: CidadeService, 
    public ngxSmartModalService: NgxSmartModalService,
    private mensagemService: MensagemService,
    private fb: FormBuilder) { 

      this.formFiltro = fb.group({
        nome: [''],
      });

    }

  ngOnInit() {
    this.pesquisar();
  }

  ngAfterViewInit() {
    const pen: Object = {
      prop1: 'test',
      prop2: true,
      prop3: [{ a: 'a', b: 'b' }, { c: 'c', d: 'd' }],
      prop4: 327652175423
    };
    this.ngxSmartModalService.setModalData(pen, 'cadCidade');
  }

  pesquisar() {
    if (!this.FiltroDTO.obj) {
        this.FiltroDTO.obj = new FiltroDTO();
    }
    this.FiltroDTO.obj = this.formFiltro.value;

    this.filtrar();
  } 

  public filtrar() {
      this.FiltroDTO.page = this.paginaSelecionada - 1;
      this.cidadeService.filtrar(this.FiltroDTO).subscribe(
          (response: any) => {
            this.cidades = response.content;
            this.totalElementos = response.totalElements;
            if (this.cidades.length === 0) this.mensagem = "Nenhuma Cidade Cadastrada!";
          },
          erro => {
            this.mensagemService.mostrar(erro);
          }
      );
  }

  public paginar(event) {
    this.paginaSelecionada = event.page;
    this.filtrar();
  } 

  public limparCampos() {
    this.formFiltro.get('nome').setValue('');
    this.FiltroDTO.obj = new FiltroDTO();
  }

  public salvarCidade(cidade: any){
    const cid: Cidade = new Cidade();
    cid.codigoCidade = cidade.id
    cid.nome = cidade.nome
    cid.pais = cidade.pais.sigla
    cid.longitude = cidade.coordenadas.lon;
    cid.latitude = cidade.coordenadas.lat;
    this.cidadeService.salvar(cid).subscribe(
      ( ) => {
        this.mensagemService.sucesso('Sucesso', 'Cidade cadastrada com Sucesso!');
        this.ngxSmartModalService.close('cadCidade');
        this.filtrar();
      },
      (erro) => {
        this.mensagemService.mostrar(erro);
      }
     )
  }

  public abrirTempo(cidade: CidadeDTO) {
    this.tempoObjDTO = new TempoObjDTO();
    this.tempoObjDTO.nomeCidade = cidade.nome;
    this.tempoObjDTO.pais = cidade.pais;
    this.tempoObjDTO.latitude = cidade.latitude;
    this.tempoObjDTO.longitude = cidade.longitude;
    this.abrirOverlayTempo = true;
  }

}
    
  