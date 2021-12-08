package com.example.demo.gameoflife;

public enum Vecino {

    SUPERIOR_IZQUIERDO(0,-1, 1),
    SUPERIOR(1,0,1),
    SUPERIOR_DERECHO(2,1,1),
    DERECHO(3,1,0),
    INFERIOR_DERECHO(4,1,-1),
    INFERIOR(5,0, -1),
    INFERIOR_IZQUIERDO(6,-1,-1),
    IZQUIERDO(7,-1,0);

    private final int posicionEnArrayVecinos;
    private final int desviacionX;
    private final int desviacionY;

    Vecino(int posicionEnArrayVecinos, int desviacionX, int desviacionY) {
        this.posicionEnArrayVecinos = posicionEnArrayVecinos;
        this.desviacionX = desviacionX;
        this.desviacionY = desviacionY;
    }

    public int getPosicionEnArrayVecinos() {
        return posicionEnArrayVecinos;
    }

    public int getDesviacionX() {
        return desviacionX;
    }

    public int getDesviacionY() {
        return desviacionY;
    }
}
