package com.ambevtech.app.util;

import com.google.common.base.Strings;
import groovy.json.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import javax.swing.text.MaskFormatter;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class Fn {

    public static boolean isEmpty(String texto) {
        return (texto == null || texto.trim().equals(""));
    }

    public static boolean isEmpty(Collection<?> list) {
        return (list == null || list.isEmpty() || list.size() == 0);
    }

    public static boolean isEmpty(Number numero) {
        if (numero == null) {
            return true;
        }

        if (numero instanceof Integer && numero.intValue() <= 0) {
            return true;
        }

        if (numero instanceof Long && numero.longValue() <= 0) {
            return true;
        }

        if (numero instanceof BigDecimal && ((BigDecimal) numero).equals(BigDecimal.ZERO)) {
            return true;
        }

        if (numero instanceof BigInteger && ((BigInteger) numero).equals(BigInteger.ZERO)) {
            return true;
        }

        return false;
    }

    public static boolean isEmpty(Object obj) {
        if (obj instanceof String) {
            return ObjectUtils.isEmpty(obj) || obj.toString().trim().isEmpty();
        }

        return ObjectUtils.isEmpty(obj);
    }

    public static boolean isEmpty(BigDecimal o) {
        return (o == null) || (o.doubleValue() == 0);
    }

    public static boolean isEmpty(Integer o) {
        return (o == null) || (o.intValue() == 0);
    }

    public static boolean isEmpty(Boolean o) {
        return (o == null) || (o.booleanValue() == false);
    }

    public static boolean isEmpty(Enum<?> o) {
        return (o == null);
    }

    public static BigDecimal stringParaBigDecimal(String valor) {
        if (isEmpty(valor)) {
            return null;
        }

        valor = valor.replaceAll("[.]", "");
        valor = valor.replaceAll("[,]", ".");

        return new BigDecimal(valor);
    }

    public static String removerAcentuacoes(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[^\\p{ASCII}]", "");
        return texto;
    }

    public static String removerMascaraTexto(String texto) {
        if (isEmpty(texto)) {
            return texto;
        }
        return texto.replaceAll("[^0-9a-zA-Z]", "");
    }

    public static boolean isNumber(String s) {
        char[] c = s.toCharArray();
        boolean d = true;
        for (int i = 0; i < c.length; i++) {
            if (!Character.isDigit(c[i])) {
                d = false;
                break;
            }
        }
        return d;
    }

    public static final LocalDate parseLocaldate(String data) {
        if (data == "" || data.isEmpty() || !data.contains("/")) {
            return null;
        }

        LocalDate novaData = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return novaData;
    }

    public static final String parseLocaldate(LocalDate data) {
        if (data == null) {
            return null;
        }

        String novaData = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return novaData;
    }

    public static final BigDecimal parseBigDecimal(String valor) {

        if (valor == "" || valor.isEmpty()) {
            return null;
        }

        BigDecimal novoValor = new BigDecimal(valor.replace(".", "").replace(",", ".").trim());

        return novoValor;
    }

    public static final String parseBigDecimal(BigDecimal valor) {
        if (valor == null) {
            return null;
        }

        return String.format("%,.2f", valor);
    }

    public static final String parseBigDecimal(BigDecimal valor, String moeda) {
        if (valor == null) {
            return null;
        }

        return moeda + " " + String.format("%,.2f", valor).trim();
    }

    public static final LocalDateTime parseLocaldateTime(String dataHoraTime) {
        if (dataHoraTime == "" || dataHoraTime.isEmpty() || !dataHoraTime.contains("/")
                || !dataHoraTime.contains(":")) {
            return null;
        }

        LocalDateTime novaDataHoraTime = LocalDateTime.parse(dataHoraTime,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        return novaDataHoraTime;
    }

    public static final String parseLocaldateTime(LocalDateTime dataHoraTime) {
        if (dataHoraTime == null) {
            return null;
        }

        String novaDataTime = dataHoraTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        return novaDataTime;
    }

    public static String somenteDigitos(String texto) {
        if (!Fn.isEmpty(texto)) {
            texto = texto.replaceAll("[^0-9]", "");
        }
        return texto;
    }

    public static String preencheCharEsq(String texto, int tamTotalTexto, char completar) {
        return StringUtils.leftPad(texto, tamTotalTexto, completar);
    }

    public static BigDecimal formataValor(BigDecimal valor) {
        BigDecimal valorRetorno = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
        if (valor != null) {
            valorRetorno = valor.setScale(2, RoundingMode.HALF_EVEN);
        }

        return valorRetorno;

    }

    public static String centralizaTexto(Integer tamanhoTotal, String texto) {
        Integer metade = tamanhoTotal / 2;
        Integer tamanho = texto.length() / 2;
        Integer espaco = (metade - tamanho);
        return Strings.padStart(texto, espaco, ' ').replace(" ","%20");
    }

    public static boolean validarSet(Set<?> lista) {
        if (Fn.isEmpty(lista)) {
            return false;
        }

        ArrayList<?> listaArray = new ArrayList<>(lista);
        if (Fn.isEmpty(listaArray)) {
            return false;
        }

        if (Fn.isEmpty(listaArray.get(0))) {
            return false;
        }

        return true;
    }

    public static boolean validaData(String data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(data, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static String convertErroObjectPro(String texto) {
        try {
            return StringEscapeUtils.unescapeJava(texto);
        } catch (Exception e) {
            return texto;
        }
    }

    public static String formataCartaoObjectPro(String numeroCartao) {
        try {
            return StringUtils.leftPad(numeroCartao, 12, "0");
        } catch (Exception e) {
            return numeroCartao;
        }
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
