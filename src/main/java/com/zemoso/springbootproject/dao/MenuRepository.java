package com.zemoso.springbootproject.dao;

import com.zemoso.springbootproject.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Item,Integer> {

public Item findById(int id);

}
