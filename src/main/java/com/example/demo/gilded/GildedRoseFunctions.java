package com.example.demo.gilded;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public final class GildedRoseFunctions {

    private static Map<String, Consumer<Item>> functions = new HashMap<>();

    private GildedRoseFunctions(){
        super();
    }

    static {
        functions.put("Aged Brie", GildedRoseFunctions::setQualityWhenItemIsAgedBrie);
        functions.put("Backstage passes to a TAFKAL80ETC concert", GildedRoseFunctions::setQualityWhenItemIsPasses);
        functions.put("Sulfuras, Hand of Ragnaros", i -> {} );
    }

    public static void applyGildedFunction(Item item){
        functions.getOrDefault(item.name, GildedRoseFunctions::setQualityWhenIsRegularItem).accept(item);
    }

    private static void setQualityWhenIsRegularItem(Item item){
        boolean isExpired = item.sellIn <= 0;
        int decrease = isExpired ? 2 : 1;
        item.quality = decreaseQuality(item.quality, decrease);
        item.sellIn-=1;
    }

    private static void setQualityWhenItemIsPasses(Item item){
        if (item.sellIn > 10){
            item.quality = increaseQuality(item.quality, 1);
        } else if (item.sellIn > 5){
            item.quality = increaseQuality(item.quality, 2);
        } else if (item.sellIn > 0){
            item.quality = increaseQuality(item.quality, 3);
        } else {
            item.quality = 0;
        }
        item.sellIn-=1;
    }

    private static void setQualityWhenItemIsAgedBrie(Item item){
        boolean isExpired = item.sellIn <= 0;
        int increase = isExpired ? 2 : 1;
        item.quality = increaseQuality(item.quality, increase);
        item.sellIn-=1;
    }

    private static int increaseQuality(int quality, int increase){
        return Math.min(quality + increase, 50);
    }

    private static int decreaseQuality(int quality, int decrease){
        return Math.max(quality - decrease, 0);
    }
}
