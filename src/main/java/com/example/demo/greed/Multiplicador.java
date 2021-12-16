package com.example.demo.greed;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Multiplicador {

    CUATRO_MODIFICADOR(4, 2),
    CINCO_MODIFICADOR(5, 4),
    SEIS_MODIFICADOR(6, 8);

    private final int repeticionesTirada;
    private final int multiplicador;
    private static Map<Integer, Multiplicador> map;

    static {
        map = Arrays.stream(values()).collect(Collectors.toMap(Multiplicador::getRepeticionesTirada, Function.identity()));
    }

    Multiplicador(int repeticionesTirada, int multiplicador) {
        this.repeticionesTirada = repeticionesTirada;
        this.multiplicador = multiplicador;
    }

    public static int fromInt(int repeticionesTirada) {
        Multiplicador multiplicador = map.get(repeticionesTirada);
        return multiplicador == null ? 1 : multiplicador.getMultiplicador();
    }

    public int getRepeticionesTirada() {
        return repeticionesTirada;
    }

    public int getMultiplicador() {
        return multiplicador;
    }
}
