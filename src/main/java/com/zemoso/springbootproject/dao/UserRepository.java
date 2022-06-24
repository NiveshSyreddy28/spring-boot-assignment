package com.zemoso.springbootproject.dao;

import com.zemoso.springbootproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,String> {


    @Query(value = "select * from users where user_name = :userName",nativeQuery = true)
    public User findbyuserName(String userName);


}
