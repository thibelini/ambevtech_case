import { ChangeDetectorRef, Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { DadosTempoDTO } from './../../../model/DadosTempoDTO';
import { MensagemService } from './../../../service/mensagem.service';
import { TempoService } from './../../../service/tempo.service';

@Component({
  selector: 'app-tempo',
  templateUrl: './tempo.component.html',
  styleUrls: ['./tempo.component.scss']
})
export class TempoComponent implements OnInit, OnChanges {

   @Input() abrirTempo: boolean;
   @Input() dados: any;
   public dadosTempo: DadosTempoDTO;
   public carregou = false;
   constructor(
      private serviceTempo: TempoService, 
      private mensagemService: MensagemService, 
      private cdr: ChangeDetectorRef) { }

   ngOnInit() {
   }

   ngOnChanges(changes: SimpleChanges): void {
      this.carregou = false;
      if (changes) {
         if (changes.abrirTempo && this.abrirTempo === true) {
            this.dadosTempo = new DadosTempoDTO();
            this.serviceTempo.dadosTempoCidade(this.dados.latitude, this.dados.longitude).subscribe(
               (response) => {
                  this.dadosTempo = response;
                  this.carregou = true;
                  this.cdr.detectChanges();
               },
               (erro) => {
                  this.mensagemService.aviso('Atenção', erro);
               }
            );
         }
      }
   }
}
