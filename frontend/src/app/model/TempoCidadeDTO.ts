import { Utils } from "../util/Utils";
import { ClimaDTO } from "./ClimaDTO";
import { TemperaturaDTO } from "./TemperaturaDTO";

export class TempoCidadeDTO {
    
    dt: number;
    temp: TemperaturaDTO;
    pressure: number;
    humidity: number;
    rain: string;
    weather: Array<ClimaDTO>;

    constructor(){
        this.temp = new TemperaturaDTO();
        this.weather = new Array<ClimaDTO>();
    }

    // public getDiaDaSemana(): string {
    //     const dataTimestamp = this.dt * 1000;
    //     const diaDaSemanaNOvo = new Date(dataTimestamp).toLocaleDateString("pt-br")
    //     return Utils.getNomeDiaDaSemana(diaDaSemanaNOvo).toString();
    // }

    
    


    // set dt(dt: string) {
    //     // debugger;
    //     // const dataTimestamp = Number(dt) * 1000;
    //     // var diaDaSemanaNOvo = new Date(dataTimestamp).toLocaleDateString("pt-br")
    //     // this.dt = Utils.getNomeDiaDaSemana(diaDaSemanaNOvo).toString();
    //     this.dt = "sss";
    // }

    // get dt():string {
    //     return this.dt;
    //   }

    // setDiaDaSemana(){
    //     const dataTimestamp = this.dt * 1000;
    //     var diaDaSemanaNOvo = new Date(dataTimestamp).toLocaleDateString("pt-br")
    //     this.diaDaSemana = Utils.getNomeDiaDaSemana(diaDaSemanaNOvo);
    // }

}