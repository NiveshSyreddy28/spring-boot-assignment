package com.zemoso.springbootproject.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
 public class ItemTest {

    @Mock
    Item item;

    private int id;
    private String itemName;

    @Test
    public void ItemTest(){
        item = new Item("Fish",200,"Yes","Non-Veg",20);
        item.setItemName("Fish");
        Assert.assertEquals("Fish",item.getItemName());
    }

    @Test
    public void getId() {
        item = new Item("Fish",200,"Yes","Non-Veg",20);
        item.setId(1);
        Assert.assertEquals(1,item.getId());
    }

    @Test
    public void getItemName() {
        item = new Item("Fish",200,"Yes","Non-Veg",20);
        Assert.assertEquals("Fish",item.getItemName());
    }

    @Test
    public void getItemPrice() {
        item = new Item("Fish", Integer.valueOf(200),"Yes","Non-Veg",20);
        Assert.assertEquals(Integer.valueOf(200),item.getItemPrice());
    }

    @Test
    public void getAvailability() {
        item = new Item("Fish",200,"Yes","Non-Veg",20);
        Assert.assertEquals("Yes",item.getAvailability());
    }

    @Test
    public void getItemType() {
        item = new Item("Fish",200,"Yes","Non-Veg",20);
        Assert.assertEquals("Non-Veg",item.getItemType());
    }

    @Test
    public void getTimeRequiredToPrepare() {
        item = new Item("Fish",Integer.valueOf(200),"Yes","Non-Veg",Integer.valueOf(20));
        Assert.assertEquals(Integer.valueOf(20),item.getTimeRequiredToPrepare());
    }

    @Test
    public void setId() {
        item = new Item("Fish",200,"Yes","Non-Veg",20);
        item.setId(2);
        Assert.assertEquals(2,item.getId());
    }

    @Test
    public void setItemName() {
        item = new Item("Fish",200,"Yes","Non-Veg",20);
        item.setItemName("Chicken");
        Assert.assertEquals("Chicken",item.getItemName());
    }

    @Test
    public void setItemPrice() {
        item = new Item("Fish",Integer.valueOf(300),"Yes","Non-Veg",20);
        item.setItemPrice(Integer.valueOf(200));
        Assert.assertEquals(Integer.valueOf(200),item.getItemPrice());
    }

    @Test
    public void setAvailability() {
        item = new Item("Fish",200,"Yes","Non-Veg",20);
        item.setAvailability("No");
        Assert.assertEquals("No",item.getAvailability());
    }

    @Test
    public void setItemType() {
        item = new Item("Fish",200,"Yes","Non-Veg",20);
        item.setItemType("Veg");
        Assert.assertEquals("Veg",item.getItemType());
    }

    @Test
    public void setTimeRequiredToPrepare() {
        item = new Item("Fish",200,"Yes","Non-Veg",Integer.valueOf(20));
        item.setTimeRequiredToPrepare(Integer.valueOf(15));
        Assert.assertEquals(Integer.valueOf(15),item.getTimeRequiredToPrepare());
    }
}