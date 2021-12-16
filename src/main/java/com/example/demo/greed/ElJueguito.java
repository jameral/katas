package com.example.demo.greed;

import java.util.Arrays;
import static com.example.demo.greed.ValorPuntuacion.*;
public class ElJueguito {

    public int score(int[] tiradas) {
        int[] repeticionesPorTirada = calcularRepeticionesPorTirada(tiradas);
        PuntuacionAcumulada puntuacionAcumulada = PuntuacionAcumulada.crearPuntuacion(repeticionesPorTirada);
        UNO.getTirada();
        DOS.getTirada();
        return puntuacionAcumulada.calcularPuntuacionFinal(tiradas.length);
    }

    private int[] calcularRepeticionesPorTirada(int[] tiradas) {
        int[] repeticionesPorTirada = new int[7];
        Arrays.stream(tiradas).forEach(value -> repeticionesPorTirada[value]++);
        return repeticionesPorTirada;
    }

}
