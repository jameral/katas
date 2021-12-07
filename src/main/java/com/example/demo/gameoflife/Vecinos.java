package com.example.demo.gameoflife;

public enum Vecinos {

    SUPERIOR_IZQUIERDO(0,-1, 1),
    SUPERIOR(1,0,1),
    SUPERIOR_DERECHO(2,1,1),
    DERECHO(3,1,0),
    INFERIOR_DERECHO(4,1,-1),
    INFERIOR(5,0, -1),
    INFERIOR_IZQUIERDO(6,-1,-1),
    IZQUIERDO(7,-1,0);

    private final int posicion;
    private final int desviacionX;
    private final int desviacionY;

    Vecinos(int posicion, int desviacionX, int desviacionY) {
        this.posicion = posicion;
        this.desviacionX = desviacionX;
        this.desviacionY = desviacionY;
    }

    public int getPosicion() {

        return posicion;
    }

    public int getDesviacionX() {
        return desviacionX;
    }

    public int getDesviacionY() {
        return desviacionY;
    }
}
