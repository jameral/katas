package com.example.demo.rpg.characters;

import com.example.demo.rpg.AbstractEntity;

public class MeleeFighter extends Character{

    int range = 2;

    @Override
    protected boolean isInRange(AbstractEntity target) {
        return super.calculateDistance(target) <= range;
    }
}
