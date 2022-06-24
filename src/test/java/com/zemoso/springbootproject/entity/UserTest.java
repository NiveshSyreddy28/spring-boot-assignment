package com.zemoso.springbootproject.entity;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
 class UserTest {

    @Mock
    User user;

    @Test
     void addItems() {

        user = new User("username","password");
        user.setItemList( new ArrayList<>());

        Item item = new Item("Fish",200,"Yes","Non-Veg",20);
        user.addItems(item);

        Assertions.assertEquals(1, user.getItemList().size());
    }

    @Test
     void getId() {
        user = new User("username","password");
        user.setId(2);
        Assert.assertEquals(2,user.getId());
    }

    @Test
     void getUserName() {
        user = new User("username","password");
        Assert.assertEquals("username",user.getUserName());
    }

    @Test
     void getPassword() {
        user = new User("username","password");
        Assert.assertEquals("password",user.getPassword());
    }

    @Test
     void getEnabled() {
        user = new User("username","password");
        user.setEnabled(1);
        Assert.assertEquals(1,user.getEnabled());
    }

    @Test
     void getItemList() {
        List<Item>itemList = new ArrayList<>();
        Item item = new Item("Fish",200,"Yes","Non-Veg",20);
        itemList.add(item);

        Mockito.when(user.getItemList()).thenReturn(Stream.of(item).collect(Collectors.toList()));
        Assertions.assertEquals(1, user.getItemList().size());
    }

    @Test
     void setId() {
        user = new User("username","password");
        user.setId(1);
        Assert.assertEquals(1,user.getId());
    }

    @Test
     void setUserName() {
        user = new User("username","password");
        user.setUserName("nivesh");
        Assert.assertEquals("nivesh",user.getUserName());
    }

    @Test
     void setPassword() {
        user = new User("username","password");
        user.setPassword("test123");
        Assert.assertEquals("test123",user.getPassword());
    }

    @Test
     void setEnabled() {
        user = new User("username","password");
        user.setEnabled(1);
        Assert.assertEquals(1,user.getEnabled());
    }

    @Test
     void setItemList() {
        List<Item>itemList = new ArrayList<>();
        Item item = new Item("Fish",200,"Yes","Non-Veg",20);
        itemList.add(item);

        Mockito.when(user.getItemList()).thenReturn(Stream.of(item).collect(Collectors.toList()));
        Assertions.assertEquals(1, user.getItemList().size());
    }
}