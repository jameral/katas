package com.example.demo.gameoflife;

import java.util.HashMap;
import java.util.Map;

public class Celda {

    private final int x;
    private final int y;
    private boolean vivo;
    private boolean siguienteEstado;
    private final Map<Vecino, Celda>vecinos = new HashMap<>();

    public Celda(int x, int y, boolean vivo) {
        this.x = x;
        this.y = y;
        this.vivo = vivo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public Map<Vecino, Celda> getVecinos() {
        return vecinos;
    }

    public boolean isSiguienteEstado() {
        return siguienteEstado;
    }

    public void setSiguienteEstado(boolean siguienteEstado) {
        this.siguienteEstado = siguienteEstado;
    }
}
