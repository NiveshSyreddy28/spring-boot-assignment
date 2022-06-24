
package com.zemoso.springbootproject;

import com.zemoso.springbootproject.controller.RestaurantController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class SpringbootprojectApplicationTests {

	@Autowired
	RestaurantController restaurantController;

	@Test
	void contextLoads() {

		assertThat(restaurantController).isNotNull();
	}


}