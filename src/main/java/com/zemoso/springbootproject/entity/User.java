package com.zemoso.springbootproject.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Column(name = "iduser")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "User name can't be empty")
    @Size(min = 3, max = 20, message = "user name should be min of 3 and max of 20 characters")
    @Column(name = "user_name")
    private String userName;

    @NotEmpty(message = "Password can't be empty")
    @Size(min = 4, max = 12, message = "Password length must be min of 4 and max of 12 ")
    @Column(name = "user_password",columnDefinition = "")
    private String password;

    @Column(name = "enabled")
    private int enabled =1;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(name = "orders",
    joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "item_id")
    )

    private List<Item> itemList;


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void addItems(Item item){
        if (itemList==null){
            itemList = new ArrayList<>();
        }
        itemList.add(item);
    }

}
