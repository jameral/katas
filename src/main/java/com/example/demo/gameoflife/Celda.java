package com.example.demo.gameoflife;

public class Celda {

    private final int x;
    private final int y;
    private boolean isAlive;
    private boolean siguienteEstado;
    private final Celda[] vecinos = new Celda[8];

    public Celda(int x, int y, boolean isAlive) {
        this.x = x;
        this.y = y;
        this.isAlive = isAlive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Celda[] getVecinos() {
        return vecinos;
    }

    public boolean isSiguienteEstado() {
        return siguienteEstado;
    }

    public void setSiguienteEstado(boolean siguienteEstado) {
        this.siguienteEstado = siguienteEstado;
    }
}
