package com.zemoso.springbootproject.controller;

import com.zemoso.springbootproject.dto.ItemDto;
import com.zemoso.springbootproject.entity.Item;
import com.zemoso.springbootproject.entity.User;
import com.zemoso.springbootproject.service.MenuServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
  class RestaurantControllerTest {

    private MockMvc mockMvc;
    @MockBean
    private MenuServiceImpl menuService;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Mock
    Item item;
   @Mock
   ItemDto itemDto;
    @Mock
    User user;
    @Mock
    List<Item>items;

    @org.junit.jupiter.api.BeforeEach
    public void setup()
    {
        RestaurantController restaurantController = new RestaurantController(menuService);
        mockMvc= MockMvcBuilders.standaloneSetup(menuService).build();
    }


    @Test
      void welcomePage() throws Exception {
       MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/restaurant/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("Home"));
    }

    @Test
     void afterLoginPage() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(get("/restaurant/loginHome")).andExpect(status().is(200))
                .andExpect(view().name("after-login"));
    }

    @Test
     void getAccessDenied() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(get("/restaurant/accessDenied")).andExpect(status().is(200))
                .andExpect(view().name("access-denied"));
    }

    @Test
     void listItems() throws Exception {
       MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
       mockMvc.perform(get("/restaurant/updateMenu")).andExpect(status().is(200))
               .andExpect(view().name("admin/configure-items-list"));
    }

    @Test
     void showFormForAdd() throws Exception {
       MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
       mockMvc.perform(get("/restaurant/showFormForAddItem")).andExpect(status().is(200))
               .andExpect(view().name("admin/add-item-form"));

    }

    @Test
     void updateItem() throws Exception {
       Item item = new Item("Chicken",200,"Yes","Non-Veg",20);

       Mockito.when(menuService.findByItemId(0)).thenReturn(item);
       MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
       mockMvc.perform(get("/restaurant/updateItem").queryParam("itemId","0")).andExpect(status().is(200))
               .andExpect(view().name("admin/add-item-form"));
    }

    @Test
     void updateAvailability() throws Exception {
       Item item = new Item("Chicken",200,"Yes","Non-Veg",20);

       Mockito.when(menuService.findByItemId(0)).thenReturn(item);
       MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
       mockMvc.perform(get("/restaurant/updateAvailability").queryParam("itemId","0")).andExpect(status().is(200))
               .andExpect(view().name("chef/update-availability-form"));

    }

    @Test
     void saveItem() throws Exception {
       item = new Item("Fish",200,"Yes","Non-Veg",20);
       items = new ArrayList<>();
       items.add(item);
      itemDto = new ItemDto(1,"Fish",200,"Yes","Non-Veg",20);
      MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
       mockMvc.perform(post("/restaurant/saveItem").flashAttr("item",itemDto )).andExpect(status().is(302)).andExpect(view().name("redirect:/restaurant/menu"));

    }

    @Test
     void delete() throws Exception {
       item = new Item("Fish",200,"Yes","Non-Veg",20);

       Mockito.when(menuService.findByItemId(0)).thenReturn(item);
       MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
       mockMvc.perform(get("/restaurant/deleteItem").queryParam("itemId","0")).andExpect(status().is(302))
               .andExpect(view().name("redirect:/restaurant/menu"));
    }

    @Test
     void itemsMenu() throws Exception {

       List<Item>items = new ArrayList<>();
       items.add(new Item("Chicken",200,"Yes","Non-Veg",20));
       Mockito.when(menuService.findAllItems()).thenReturn(items);
       MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
       mockMvc.perform(get("/restaurant/menu")).andExpect(status().is(200))
               .andExpect(view().name("default-menu"));

    }

    @Test
     void chefList() throws Exception {

       List<Item>items = new ArrayList<>();
       items.add(new Item("Chicken",200,"Yes","Non-Veg",20));
       Mockito.when(menuService.findAllItems()).thenReturn(items);
       MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
       mockMvc.perform(get("/restaurant/chefMenu")).andExpect(status().is(200))
               .andExpect(view().name("chef/chef-menu"));
    }


    @Test
     void addItemToOrder() throws Exception {
       item = new Item("Chicken",200,"Yes","Non-Veg",20);
       user = new User("username","password");

       Mockito.when(menuService.findUserByName("username")).thenReturn(user);
       Mockito.when(menuService.findByItemId(1)).thenReturn(item);
       MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
       user.setItemList( new ArrayList<>());
       user.addItems(item);
       Assertions.assertEquals(1, user.getItemList().size());
       mockMvc.perform(get("/restaurant/addItemToOrder")).andExpect(status().is(400));
    }


//    @Test
//    public void viewUserOrders() throws Exception {
//       user = new User("username","password");
//       List<Item>itemList = new ArrayList<>();
//       Item item = new Item("Fish",200,"Yes","Non-Veg",20);
//       itemList.add(item);
//
//       Mockito.when(menuService.findUserByName("username")).thenReturn(user);
//       Assertions.assertEquals("username",menuService.findUserByName("username").getUserName());
//
//       Mockito.when(user.getItemList()).thenReturn(Stream.of(item).collect(Collectors.toList()));
//       Assertions.assertEquals(1, user.getItemList().size());
//
//       mockMvc.perform(get("/restaurant/viewUserOrders")).andExpect(status().is(200))
//               .andExpect(view().name("user/view-orders.html"));
//    }
}