package com.swiftshop.inventory;

import com.basedomain.basedomain.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
		Test test = new Test();
		test.getTestVariable();
	}

}
