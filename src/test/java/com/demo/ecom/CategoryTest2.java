package com.demo.ecom;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.demo.ecom.entity.Category;
import com.demo.ecom.repository.CategoryRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CategoryTest2 {

	@Autowired
	CategoryRepository repo;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void shouldSuccessOneCategory() {
		Category c = new Category();
		c.setName("myat");
		c.setCreated_date(System.currentTimeMillis());
		c.setUpdated_date(System.currentTimeMillis());
		repo.save(c);
		assertNotNull(c);
	}
}
