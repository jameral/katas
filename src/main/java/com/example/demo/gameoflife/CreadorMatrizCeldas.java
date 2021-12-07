package com.example.demo.gameoflife;

public class CreadorMatrizCeldas {

    private final char[] estado;
    private final int ancho;
    private final int limiteSuperiorY;
    private final int limiteSuperiorX;
    private final Celda[][] celdas;

    public CreadorMatrizCeldas(int alto, int ancho, String estadoInicial) {
        super();
        this.ancho = ancho;
        this.limiteSuperiorY = alto -1;
        this.limiteSuperiorX = ancho -1;
        estado = estadoInicial.toCharArray();
        celdas = new Celda[ancho][alto];
        crearFilas();
    }

    private void crearFilas(){
        for (int fila = limiteSuperiorY; fila>= 0; fila--){
            crearFila(fila);
        }
    }

    private void crearFila(int fila) {
        for (int columna=0;columna<ancho;columna++){
            Celda celda = crearCeldaSiNoExiste(fila, columna);
            setVecinos(celda);
        }
    }

    private char getEstado(int y, int x){
        int filaPedida = limiteSuperiorY - y;
        return estado[filaPedida * this.ancho + x];
    }

    private Celda crearCeldaSiNoExiste(int fila, int columna) {
        Celda celda = celdas[columna][fila];
        if (celda == null){
            char estado = getEstado(fila, columna);
            celda = new Celda(columna, fila, estado == '*');
            celdas[columna][fila] = celda;
        }
        return celda;
    }

    private void setVecinos(Celda celda){
        Vecinos[] vecinos = Vecinos.values();
        Celda[] celdasVecinas = celda.getVecinos();
        for (Vecinos vecino : vecinos){
            celdasVecinas[vecino.getPosicion()] = recuperarVecino(celda, vecino);
        }
    }

    public Celda recuperarVecino(Celda celda, Vecinos vecino){
        int posY = celda.getY() + vecino.getDesviacionY();
        int posX = celda.getX() + vecino.getDesviacionX();
        if (posicionValida(posY, posX)){
            return crearCeldaSiNoExiste(posY, posX);
        }
        return null;
    }

    private boolean posicionValida(int posY, int posX) {
        return posY <= limiteSuperiorY && posY >= 0 && posX <= limiteSuperiorX && posX >= 0;
    }

    public Celda[][] getCeldas() {
        return celdas;
    }
}
