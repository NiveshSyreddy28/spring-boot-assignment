package com.zemoso.springbootproject.service;

import com.zemoso.springbootproject.entity.Item;
import com.zemoso.springbootproject.entity.User;

import java.util.List;

public interface MenuService {
    List<Item> findAllItems();

    public Item findByItemId(int id);

    public void saveItem(Item item);

    public void deleteItemById(int id);

    public void saveUser(User user);

    public User findUserByName(String userName);

}


