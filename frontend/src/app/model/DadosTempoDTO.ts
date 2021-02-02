import { AlertasTempoDTO } from "./AlertasTempoDTO";
import { TempoAtualDTO } from "./TempoAtualDTO";
import { TempoCidadeDTO } from "./TempoCidadeDTO";

export class DadosTempoDTO {

    current: TempoAtualDTO;
    daily: Array<TempoCidadeDTO>;
    alerts: Array<AlertasTempoDTO>;

    constructor(){
        this.daily = new Array<TempoCidadeDTO>();
        this.alerts = new Array<AlertasTempoDTO>();
    }

}