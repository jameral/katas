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
        Arrays.stream(celdas).parallel().forEach(this::calcularSiguienteEstadoParaFila);
        Arrays.stream(celdas).parallel().forEach(this::asignarNuevoEstadoParaFila);
    }

    private void calcularSiguienteEstadoParaFila(Celda[] celdas) {
        for (Celda celda : celdas){
            calcularSiguienteEstadoDeCelda(celda);
        }
    }

    private void calcularSiguienteEstadoDeCelda(Celda celda){
        long vecinosVivos = calcularVecinosVivosDeCelda(celda);
        boolean celdaVive = celda.isVivo();
        boolean faltaPoblacion = vecinosVivos < 2;
        boolean sobraPoblacion = vecinosVivos > 3;
        if (celdaVive && (faltaPoblacion || sobraPoblacion)){
            celda.setSiguienteEstado(false);
        } else if (!celdaVive && vecinosVivos == 3){
            celda.setSiguienteEstado(true);
        } else {
            celda.setSiguienteEstado(celda.isVivo());
        }
    }

    private long calcularVecinosVivosDeCelda(Celda celda){
        return celda.getVecinos().values().stream()
                .filter(Objects::nonNull)
                .filter(Celda::isVivo)
                .count();
    }

    private void asignarNuevoEstadoParaFila(Celda[] celdas) {
        for (Celda celda : celdas){
            boolean siguienteEstado = celda.isSiguienteEstado();
            celda.setVivo(siguienteEstado);
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(alto * ancho + alto + 6);
        sb.append(ancho).append("   ").append(alto).append("\n");
        for (int i= alto-1;i>=0;i--){
            for (int j=0;j<=ancho-1;j++){
                Celda celda = celdas[j][i];
                sb.append(celda.isVivo() ? ALIVE : DEAD);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
