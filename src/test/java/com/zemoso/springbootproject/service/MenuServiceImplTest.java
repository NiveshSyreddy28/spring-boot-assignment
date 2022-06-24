package com.zemoso.springbootproject.service;

import com.zemoso.springbootproject.dao.MenuRepository;
import com.zemoso.springbootproject.dao.UserRepository;
import com.zemoso.springbootproject.entity.Item;
import com.zemoso.springbootproject.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
 class MenuServiceImplTest {

    @MockBean
    MenuRepository menuRepository;
    @MockBean
    UserRepository userRepository;
    @Autowired
    MenuServiceImpl menuServiceImpl;
    @Mock
    Item item;
    @Mock
    User user;
    @Test
     void findAllItems() {

        item = new Item("Chicken",200,"Yes","Non-veg",20);
        Mockito.when(menuServiceImpl.findAllItems()).thenReturn(Stream.of(item).collect(Collectors.toList()));
        Assertions.assertEquals(1, menuRepository.findAll().size());
    }

    @Test
     void findByItemId() {
        item = new Item("Chicken",200,"Yes","Non-veg",20);

        Mockito.when(menuServiceImpl.findByItemId(1)).thenReturn(item);
        Assertions.assertEquals("Chicken",menuRepository.findById(1).getItemName());
    }

    @Test
     void saveItem() {
        item = new Item("Chicken",200,"Yes","Non-veg",20);
        menuServiceImpl.saveItem(item);

        Mockito.verify(menuRepository,Mockito.times(1)).save(item);
    }

    @Test
     void deleteItemById() {
        int id = 1;
        menuServiceImpl.deleteItemById(id);

        Mockito.verify(menuRepository,Mockito.times(1)).deleteById(id);
    }

    @Test
     void saveUser() {

        user = new User("username","password");
        menuServiceImpl.saveUser(user);

        Mockito.verify(userRepository,Mockito.times(1)).save(user);

    }

    @Test
     void findUserByName() {

        user = new User("username","password");

        Mockito.when(userRepository.findbyuserName("username")).thenReturn(user);
        assertThat(menuServiceImpl.findUserByName("username")).isEqualTo(user);

    }
}
