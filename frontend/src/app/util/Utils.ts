import * as moment from "moment";

export class Utils {
    
    public static left(str: string, n: number) {
        if (n <= 0) {
            return '';
        } else if (n > String(str).length) {
            return str;
        } else {
            return String(str).substring(0, n);
        }
    }

    public static right(str: string, n: number) {
        if (n <= 0) {
            return '';
        } else if (n > String(str).length) {
            return str;
        } else {
            const iLen = String(str).length;
            return String(str).substring(iLen, iLen - n);
        }
    }
    /**
     * Thiago Belini
     * @param data no Formato dd/mm/yyyy
     */
    public static getNomeDiaDaSemana(data: string) {
        const ano = this.right(data, 4);
        const dia = this.left(data, 2);
        const mes = data.substr(3, 2);
        const dataFormatada = ano + '-' + mes + '-' + dia;
        const week  = ['Domingo', 'Segunda-feira', 'Terça-feira', 'Quarta-feira', 'Quinta-feira', 'Sexta-feira', 'Sábado'];
        const date = moment(dataFormatada);
        const dow = date.day();
        const semana = week[dow];
        return semana;
    }

    public static formataDiaSemanaTimestemp(dataTimestamp: number) {
        const dataTimestampCompleta = dataTimestamp * 1000;
        const diaDaSemana = new Date(dataTimestampCompleta).toLocaleDateString("pt-br")
        return Utils.getNomeDiaDaSemana(diaDaSemana).toString();
     }

}
