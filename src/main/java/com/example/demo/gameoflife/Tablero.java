package com.example.demo.gameoflife;

import java.util.Arrays;
import java.util.Objects;

public class Tablero {

    private final Celda[][] celdas;
    private final int alto;
    private final int ancho;

    public static final String ALIVE = "*";
    public static final String DEAD = ".";

    public Tablero(int alto, int ancho, String estadoInicial){
        super();
        this.alto = alto;
        this.ancho = ancho;
        celdas = new CreadorMatrizCeldas(alto, ancho, estadoInicial).getCeldas();
    }

    public void siguienteGeneracion(){
        Arrays.stream(celdas).forEach(this::calcularSiguienteEstadoParaFila);
        Arrays.stream(celdas).forEach(this::asignarNuevoEstadoParaFila);
    }

    private void calcularSiguienteEstadoParaFila(Celda[] celdas) {
        for (Celda celda : celdas){
            calculaSiguienteEstado(celda);
        }
    }

    private void calculaSiguienteEstado(Celda celda){
        long vecinosVivos = calculaVecinosVivosDeCelda(celda);
        boolean celdaVive = celda.isAlive();
        boolean faltaPoblacion = vecinosVivos < 2;
        boolean sobrePoblacion = vecinosVivos > 3;
        if (celdaVive && (faltaPoblacion || sobrePoblacion)){
            celda.setSiguienteEstado(false);
        } else if (!celdaVive && vecinosVivos == 3){
            celda.setSiguienteEstado(true);
        } else {
            celda.setSiguienteEstado(celda.isAlive());
        }
    }

    private long calculaVecinosVivosDeCelda(Celda celda){
        return celda.getVecinos().values().stream()
                .filter(Objects::nonNull)
                .filter(Celda::isAlive)
                .count();
    }

    private void asignarNuevoEstadoParaFila(Celda[] celdas) {
        for (Celda celda : celdas){
            boolean siguienteEstado = celda.isSiguienteEstado();
            celda.setAlive(siguienteEstado);
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(ancho).append("   ").append(alto).append("\n");
        for (int i= alto-1;i>=0;i--){
            for (int j=0;j<=ancho-1;j++){
                Celda celda = celdas[j][i];
                sb.append(celda.isAlive() ? ALIVE : DEAD);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
