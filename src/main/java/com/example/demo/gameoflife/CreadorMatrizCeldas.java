package com.example.demo.gameoflife;


import java.util.Map;
import java.util.Optional;

public class CreadorMatrizCeldas {

    private final char[] estado;
    private final int ancho;
    private final int alto;
    private final int limiteSuperiorY;
    private final int limiteSuperiorX;
    private final Celda[][] celdas;

    public CreadorMatrizCeldas(int alto, int ancho, String estadoInicial) {
        super();
        estado = estadoInicial.toCharArray();
        this.ancho = ancho;
        this.alto = alto;
        limiteSuperiorY = alto -1;
        limiteSuperiorX = ancho -1;
        celdas = new Celda[ancho][alto];
        crearFilas();
    }

    private void crearFilas(){
        for (int y = 0; y<alto; y++){
            crearFila(y);
        }
    }

    private void crearFila(int y) {
        for (int x=0;x<ancho;x++){
            Celda celda = recuperarCeldaOCrearlaSiNoExiste(x, y);
            establecerVecinos(celda);
        }
    }

    private Celda recuperarCeldaOCrearlaSiNoExiste(int x, int y) {
        return recuperarCelda(x, y).orElseGet(() -> crearCelda(x,y));
    }

    private void establecerVecinos(Celda celda){
        PosicionesVecinas[] posicionesVecinas = PosicionesVecinas.values();
        Map<PosicionesVecinas, Celda> celdasVecinas = celda.getVecinos();
        for (PosicionesVecinas posicionVecina : posicionesVecinas){
            Celda celdaVecina = recuperarVecino(celda, posicionVecina);
            celdasVecinas.put(posicionVecina, celdaVecina);
        }
    }

    public Celda recuperarVecino(Celda celda, PosicionesVecinas posicionesVecinas){
        int y = celda.getY() + posicionesVecinas.getDesviacionY();
        int x = celda.getX() + posicionesVecinas.getDesviacionX();
        if (posicionValida(x, y)){
            return recuperarCeldaOCrearlaSiNoExiste(x, y);
        }
        return null;
    }

    private boolean posicionValida(int x, int y) {
        return y <= limiteSuperiorY && y >= 0 && x <= limiteSuperiorX && x >= 0;
    }

    private Optional<Celda> recuperarCelda(int x, int y){
        return Optional.ofNullable(celdas[x][y]);
    }

    private Celda crearCelda(int x, int y) {
        char estado = obtenerEstadoDeArrayAPartirDeCoordenadasDeMatriz(x, y);
        boolean isAlive = estado == '*';
        Celda celda = new Celda(x, y, isAlive);
        celdas[x][y] = celda;
        return celda;
    }

    private char obtenerEstadoDeArrayAPartirDeCoordenadasDeMatriz(int x, int y){
        int filaPedida = limiteSuperiorY - y;
        int posicionInicioFilaEnArray = filaPedida * ancho;
        int posicionFinalEnArray = posicionInicioFilaEnArray + x;
        return estado[posicionFinalEnArray];
    }

    public Celda[][] getCeldas() {
        return celdas;
    }
}
