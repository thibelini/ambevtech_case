import { Utils } from './../../../util/Utils';
import { DadosTempoDTO } from './../../../model/DadosTempoDTO';
import { MensagemService } from './../../../service/mensagem.service';
import { TempoService } from './../../../service/tempo.service';
import { OnChanges, SimpleChanges } from '@angular/core';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-tempo',
  templateUrl: './tempo.component.html',
  styleUrls: ['./tempo.component.scss']
})
export class TempoComponent implements OnInit, OnChanges {

   @Input() abrirTempo: boolean;
   @Input() dados: any;
   public dadosTempo: DadosTempoDTO;

   constructor(private serviceTempo: TempoService, private mensagemService: MensagemService) { }

   ngOnInit() {
   }

   ngOnChanges(changes: SimpleChanges): void {
      if (changes) {
         if (changes.abrirTempo && this.abrirTempo) {
            this.dadosTempo = new DadosTempoDTO();
            this.serviceTempo.dadosTempoCidade(this.dados.latitude, this.dados.longitude).subscribe(
               (response) => {
                  this.dadosTempo = response;
                  console.log(response)
               },
               (erro) => {
                  this.mensagemService.aviso('Atenção', erro);
               }
            );
         }
      }
   }
}
