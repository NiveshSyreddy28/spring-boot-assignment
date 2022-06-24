package com.zemoso.springbootproject.controller;

import com.zemoso.springbootproject.dto.ItemDto;
import com.zemoso.springbootproject.entity.Item;
import com.zemoso.springbootproject.entity.User;
import com.zemoso.springbootproject.service.MenuService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private ModelMapper modelMapper;
    private MenuService menuService;
    private List<String> availabilityOptions;
    private List<String> itemTypeOptions;

    private static final String ADD_ITEM_FORM = "admin/add-item-form";
    private static final String AVAILABLE_OPTIONS = "availabilityOptions";
    private static final String TYPE_OPTIONS = "typeOptions";

    private static final String ITEMS = "items";
    @PostConstruct
    public void addAvailabilityOptions(){
        availabilityOptions = new ArrayList<>();
        availabilityOptions.add("Yes");
        availabilityOptions.add("No");

        itemTypeOptions = new ArrayList<>();
        itemTypeOptions.add("Veg");
        itemTypeOptions.add("Non-Veg");
    }

    @Autowired
    public RestaurantController(MenuService menuService) {

        this.menuService = menuService;
    }

    @GetMapping("/home")
    public String welcomePage(){

        return "Home";
    }
    @GetMapping("/loginHome")
    public String afterLoginPage(){

        return "after-login";
    }

    @GetMapping("/accessDenied")
    public String getAccessDenied(){

        return "access-denied";
    }

    @GetMapping("/updateMenu")
    public String listItems(Model model){

        List<Item> itemsList = menuService.findAllItems();

        model.addAttribute(ITEMS, itemsList);

        return "admin/configure-items-list";

    }
    @GetMapping("/showFormForAddItem")
    public String showFormForAdd(Model model){

        model.addAttribute(AVAILABLE_OPTIONS, availabilityOptions);
        model.addAttribute(TYPE_OPTIONS, itemTypeOptions);

        Item item = new Item();
        model.addAttribute("item", item);

        return ADD_ITEM_FORM;

    }

@GetMapping("/updateItem")
public String updateItem(@RequestParam("itemId") int itemId, Model model){

        Item item = menuService.findByItemId(itemId);
        model.addAttribute("item", item);
        model.addAttribute(AVAILABLE_OPTIONS,availabilityOptions);
        model.addAttribute(TYPE_OPTIONS,itemTypeOptions);

        return ADD_ITEM_FORM;
    }
    @GetMapping("/updateAvailability")
    public String updateAvailability(@RequestParam("itemId")int id, Model model){
        Item item = menuService.findByItemId(id);
        model.addAttribute("item", item);
        model.addAttribute(AVAILABLE_OPTIONS,availabilityOptions);
        return "chef/update-availability-form";
    }
    @PostMapping("/saveItem")
    public String saveItem(@Valid @ModelAttribute("item") ItemDto item, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute(TYPE_OPTIONS,itemTypeOptions);
            model.addAttribute(AVAILABLE_OPTIONS,availabilityOptions);
            return ADD_ITEM_FORM;

        }
        // convert DTO to entity
        Item theItem = modelMapper.map(item, Item.class);
        try {
            menuService.saveItem(theItem);
        }
        catch (Exception e){
            return "exceptions/duplicate-item-name-exception";
        }
        menuService.saveItem(theItem);
        return "redirect:/restaurant/menu";
    }


    @GetMapping("/deleteItem")
    public String delete(@RequestParam("itemId") int id){

        menuService.deleteItemById(id);

        return "redirect:/restaurant/menu";
    }

    @GetMapping("/menu")
    public String itemsMenu(Model model){

        List<Item> itemsList = menuService.findAllItems();

        model.addAttribute(ITEMS, itemsList);

        return "default-menu";

    }

    @GetMapping("/chefMenu")
    public String chefList(Model model){

        List<Item> itemsList = menuService.findAllItems();

        model.addAttribute(ITEMS, itemsList);

        return "chef/chef-menu";

    }
    @GetMapping("/addItemToOrder")
    public String addItemToOrder(@RequestParam("itemId")int itemId, @RequestParam("userName")String userName, Model model){

        User user = menuService.findUserByName(userName);

        Item item = menuService.findByItemId(itemId);

        user.addItems(item);

        menuService.saveUser(user);

        model.addAttribute("userName",userName);

        return "redirect:/restaurant/viewUserOrders";
    }
    @GetMapping("/viewUserOrders")
    public String viewUserOrders(Model model ){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User user = menuService.findUserByName(userName);

        List<Item>itemsList = user.getItemList();
        model.addAttribute(ITEMS,itemsList);
        return "user/view-orders.html";
    }

}
