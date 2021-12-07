package com.example.demo.gilded;

import java.util.Arrays;

public class GildedRose {

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality(){
        Arrays.stream(items).forEach(GildedRoseFunctions::applyGildedFunction);
    }
}