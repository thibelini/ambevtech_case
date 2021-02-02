import { ClimaDTO } from "./ClimaDTO";

export class TempoAtualDTO {

    dt: number;
    temp: number;
    pressure: number;
    humidity: number;
    weather: Array<ClimaDTO>;
    diaDaSemana: string = '';

    constructor(){
        this.weather = new Array<ClimaDTO>();
    }

}