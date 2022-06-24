package com.zemoso.springbootproject.service;


import com.zemoso.springbootproject.dao.MenuRepository;
import com.zemoso.springbootproject.dao.UserRepository;
import com.zemoso.springbootproject.entity.Item;
import com.zemoso.springbootproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository;
    private UserRepository userRepository;


    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository, UserRepository userRepository){

        this.menuRepository = menuRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<Item> findAllItems() {

        return menuRepository.findAll();
    }

    @Override
    public Item findByItemId(int id) {

        return menuRepository.findById(id);

    }
    @Override
    public void saveItem(Item item) {

        menuRepository.save(item);
    }

    @Override
    public void deleteItemById(int id) {
        menuRepository.deleteById(id);

    }

    @Override
    public void saveUser(User user) {

        userRepository.save(user);

    }

    @Override
    public User findUserByName(String userName) {

        return userRepository.findbyuserName(userName);
    }


}
