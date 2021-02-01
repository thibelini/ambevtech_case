import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { MensagemService } from '../../../service/mensagem.service';

@Component({
    selector: 'app-mensagem',
    templateUrl: './mensagem.component.html',
    styleUrls: ['./mensagem.component.scss']
})
export class MensagemComponent implements OnInit {

    msg: any = {
        tipo: '',
        titulo: '',
        descricao: ''
    };

    constructor(private service: ToastrService) {

        const defaultOptions = {
            preventDuplicates: true,
            enableHtml: true,
            disableTimeOut: false,
            closeButton: true,
            tapToDismiss: false,
            maxOpened: 3,
            timeOut: 5000
        };

        MensagemService.mensagens.subscribe(
            value => {
                this.msg = value;

                switch (this.msg.tipo) {
                    case 'info':
                        this.service.info(this.msg.descricao, this.msg.titulo, defaultOptions);
                        break;
                    case 'success':
                        this.service.success(this.msg.descricao, this.msg.titulo, defaultOptions);
                        break;
                    case 'error':
                        this.service.error(this.msg.descricao, this.msg.titulo, defaultOptions);
                        break;
                    case 'warn':
                        this.service.warning(this.msg.descricao, this.msg.titulo, defaultOptions);
                        break;
                }
            },
            () => {
                this.msg = {
                    tipo: '',
                    titulo: '',
                    descricao: ''
                };
            }
        );

    }

    ngOnInit() {
    }

}
