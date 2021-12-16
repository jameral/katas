package com.example.demo.greed;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ValorPuntuacion {

    UNO(1, 1000, 100),
    DOS(2, 200, 0),
    TRES(3, 300, 0),
    CUATRO(4, 400, 0),
    CINCO(5, 500, 50),
    SEIS(6, 600, 0);

    private final int tirada;
    private final int puntuacionTriple;
    private final int puntuacionIndividual;
    private static Map<Integer, ValorPuntuacion> map;

    static {
        map = Arrays.stream(values()).collect(Collectors.toMap(ValorPuntuacion::getTirada, Function.identity()));
    }

    ValorPuntuacion(int tirada, int puntuacionTriple, int puntuacionIndividual) {
        this.tirada = tirada;
        this.puntuacionTriple = puntuacionTriple;
        this.puntuacionIndividual = puntuacionIndividual;
    }

    public int getTirada() {
        return this.tirada;
    }

    public int getPuntuacionTriple() {
        return puntuacionTriple;
    }

    public int getPuntuacionIndividual() {
        return puntuacionIndividual;
    }

    public static ValorPuntuacion fromInt(int value) {
        return map.get(value);
    }
}
