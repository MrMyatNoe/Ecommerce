//package com.demo.ecom;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//import com.demo.ecom.entity.Category;
//import com.demo.ecom.repository.CategoryRepository;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@TestMethodOrder(OrderAnnotation.class)
//public class CategoryTest {
//
//	Category c = null;
//	
//	@Autowired
//	CategoryRepository cateRepo;
//	
//	@BeforeEach
//	public void setUp() {
//		c = new Category();
//	}
//	
//	@Test
//	@Rollback(value = false)
//	@Order(1)
//	public void testCreateCategory() {
//		c.setName("ho");
//		c.setCreated_date(System.currentTimeMillis());
//		c.setUpdated_date(System.currentTimeMillis());
//		cateRepo.save(c);
//		assertNotNull(c);
//	}
//	
//	@Test
//	@Order(5)
//	public void testFindCategoryByNameExists() {
//		String name = "Thet";
//		c = cateRepo.findByName(name);
//		assertThat(c.getName().equals(name));
//	}
//	
//	@Test
//	@Order(6)
//	public void testFindCategoryByNameNotExists() {
//		String name = "Jhonny";
//		c = cateRepo.findByName(name);
//		assertNull(c);
//	}
//	
//	@Test
//	@Rollback(false)
//	@Order(3)
//	public void testUpdateCategory() {
//		String name = "GG";
//		c.setId(2);
//		c.setName(name);
//		c.setCreated_date(System.currentTimeMillis());
//		c.setUpdated_date(System.currentTimeMillis());
//		cateRepo.save(c);
//		
//		c = cateRepo.findByName(name);
//		assertThat(c.getName().equals(name));
//	}
//	
//	@Test
//	@Order(4)
//	public void testListCategories() {
//		List<Category> list = cateRepo.findAll();
//		list.stream().filter(s->s.getId()>0).collect(Collectors.toList());
//		list.forEach(System.out::println);
//		assertThat(list).size().isGreaterThan(0);
//	}
//	
//	@Test
//	@Rollback(false)
//	@Order(2)
//	public void testDeleteCategory() {
//		long id = 12;
//		boolean isExistBeforeDelete = cateRepo.findById(id).isPresent();
//		cateRepo.deleteById(id);
//		boolean notExistAfterDelete = cateRepo.findById(id).isPresent();
//		assertTrue(isExistBeforeDelete);
//		assertFalse(notExistAfterDelete);
//	}
//}
