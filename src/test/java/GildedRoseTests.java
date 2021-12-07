import com.example.demo.gilded.GildedRose;
import com.example.demo.gilded.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseTests {

    @Test
    void updateQualityMustUpdateQualityAndSellIn(){
        Item[] items = new Item[] { new Item("foo", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }

    @Test
    void qualityMustDegradeTwiceAsFastWhenItemIsExpired(){
        Item[] items = new Item[] { new Item("foo", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }

    @Test
    void qualityCannotBeNegative(){
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }

    @Test
    void agedBrieIncreasesQualityWhenAging(){
        Item[] items = new Item[] { new Item("Aged Brie", 10, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }
    @Test
    void agedBrieIncreasesQualityTwiceAsFastWhileBeingExpired(){
        Item[] items = new Item[] { new Item("Aged Brie", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }
    @Test
    void qualityCannotBeGreaterThan50(){
        Item[] items = new Item[] { new Item("Aged Brie", 0, 49)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }

    @Test
    void sulfurasRetainsSellInAndQuality(){
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 49)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(49, items[0].quality);
        assertEquals(10, items[0].sellIn);
    }

    @Test
    void backstagePassesIncreaseQuality(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 20, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, items[0].quality);
        assertEquals(19, items[0].sellIn);
    }

    @Test
    void backstagePassesIncreaseMoreQualityWhenSellInIsBetween5and10(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }

    @Test
    void backstagePassesIncreaseEvenMoreQualityWhenSellInIsBetween0And5(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, items[0].quality);
        assertEquals(4, items[0].sellIn);
    }

    @Test
    void backstagePassesHaveNoQualityWhenSellInIs0(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }
}
