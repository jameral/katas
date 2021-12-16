package com.example.demo.gameoflife;

public enum PosicionesVecinas {

    SUPERIOR_IZQUIERDO(-1, 1),
    SUPERIOR(0,1),
    SUPERIOR_DERECHO(1,1),
    DERECHO(1,0),
    INFERIOR_DERECHO(1,-1),
    INFERIOR(0, -1),
    INFERIOR_IZQUIERDO(-1,-1),
    IZQUIERDO(-1,0);

    private final int desviacionX;
    private final int desviacionY;

    PosicionesVecinas(int desviacionX, int desviacionY) {
        this.desviacionX = desviacionX;
        this.desviacionY = desviacionY;
    }
    
    public int getDesviacionX() {
        return desviacionX;
    }

    public int getDesviacionY() {
        return desviacionY;
    }
}
