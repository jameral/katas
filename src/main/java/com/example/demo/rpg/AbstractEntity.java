package com.example.demo.rpg;

public abstract class AbstractEntity {

    protected int health;
    protected boolean alive;
    protected int[] position = new int[2];
    protected int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setPosition(int x, int y){
        position[0] = x;
        position[1] = y;
    }
    public int[]getPosition(){
        return this.position;
    }

}
