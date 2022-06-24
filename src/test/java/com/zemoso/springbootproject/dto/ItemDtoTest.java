package com.zemoso.springbootproject.dto;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class ItemDtoTest {

    @Mock
    ItemDto itemDto;
    @Test
    public void ItemDtoTest(){
        itemDto = new ItemDto();
        itemDto.setId(1);
        itemDto.setItemName("Fish");
        itemDto.setItemType("Non-Veg");
        itemDto.setAvailability("Yes");
        itemDto.setItemPrice(250);
        itemDto.setTimeRequiredToPrepare(20);
        assertEquals("Fish",itemDto.getItemName());
        assertEquals(Integer.valueOf(250),itemDto.getItemPrice());
        assertEquals("Yes",itemDto.getAvailability());
        assertEquals("Non-Veg",itemDto.getItemType());
        assertEquals(Integer.valueOf(20),itemDto.getTimeRequiredToPrepare());
    }
//    @Test
//    public void getId() {
//    }
//
//    @Test
//    public void getItemName() {
//    }
//
//    @Test
//    public void getItemPrice() {
//    }
//
//    @Test
//    public void getAvailability() {
//    }
//
//    @Test
//    public void getItemType() {
//    }
//
//    @Test
//    public void getTimeRequiredToPrepare() {
//    }
//
//    @Test
//    public void setId() {
//    }
//
//    @Test
//    public void setItemName() {
//    }
//
//    @Test
//    public void setItemPrice() {
//    }
//
//    @Test
//    public void setAvailability() {
//    }
//
//    @Test
//    public void setItemType() {
//    }
//
//    @Test
//    public void setTimeRequiredToPrepare() {
//    }
}