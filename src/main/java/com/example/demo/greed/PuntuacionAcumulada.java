package com.example.demo.greed;

import lombok.Data;

@Data
public class PuntuacionAcumulada {

    private static final int BONIFICACION_POR_TRES_PARES = 800;
    private static final int BONIFICACION_ESCALERA = 1200;
    private int puntuacionTotal;
    private int numeroDePares;

    public static PuntuacionAcumulada crearPuntuacion(int[] repeticionesPorTirada) {
        PuntuacionAcumulada puntuacionAcumulada = new PuntuacionAcumulada();
        for (int tirada = 1; tirada < repeticionesPorTirada.length; tirada++) {
            ValorPuntuacion valorPuntuacion = ValorPuntuacion.fromInt(tirada);
            puntuacionAcumulada.actualizarPuntuacion(valorPuntuacion, repeticionesPorTirada[tirada]);
        }
        return puntuacionAcumulada;
    }

    public void actualizarPuntuacion(ValorPuntuacion valorPuntuacion, int repeticionesTirada) {
        if (repeticionesTirada < 3) {
            actualizarPuntuacionYPares(repeticionesTirada, valorPuntuacion.getPuntuacionIndividual());
        } else {
            actualizarPuntuacionTriple(repeticionesTirada, valorPuntuacion.getPuntuacionTriple());
        }
    }

    private void actualizarPuntuacionYPares(int repeticionesTirada, int puntuacionTirada) {
        actualizarNumeroDeParesSiHayPar(repeticionesTirada);
        this.puntuacionTotal += puntuacionTirada * repeticionesTirada;
    }

    private void actualizarPuntuacionTriple(int repeticionesTirada, int puntuacionTirada) {
        int multiplier = Multiplicador.fromInt(repeticionesTirada);
        this.puntuacionTotal += puntuacionTirada * multiplier;
    }

    private void actualizarNumeroDeParesSiHayPar(int repeticionesTirada) {
        boolean esPar = repeticionesTirada == 2;
        if (esPar) {
            numeroDePares++;
        }
    }

    public int calcularPuntuacionFinal(int totalTiradas) {
        if (numeroDePares == 3) {
            return puntuacionTotal + BONIFICACION_POR_TRES_PARES;
        } else if (esEscalera(totalTiradas)) {
            return puntuacionTotal + BONIFICACION_ESCALERA;
        } else {
            return puntuacionTotal;
        }
    }

    private boolean esEscalera(int totalTiradas) {
        return totalTiradas == 6 && numeroDePares == 0 && puntuacionTotal == 150;
    }


}
