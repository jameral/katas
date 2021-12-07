package com.example.demo.rpg.characters;

import com.example.demo.rpg.AbstractEntity;

public class RangedFighter extends Character{

    int range = 20;

    @Override
    protected boolean isInRange(AbstractEntity target) {
        return super.calculateDistance(target) <= range;
    }

}
