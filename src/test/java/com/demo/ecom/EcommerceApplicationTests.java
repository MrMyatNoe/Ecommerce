package com.demo.ecom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demo.ecom.entity.Category;
import com.demo.ecom.repository.CategoryRepository;
import com.demo.ecom.service.ICategoryService;

@SpringBootTest
//(webEnvironment = 
//	WebEnvironment.RANDOM_PORT,
//	classes = EcommerceApplication.class
//)
//@AutoConfigureMockMvc
class EcommerceApplicationTests {

//	@Autowired
//	MockMvc mockMvc;

	@Autowired
	ICategoryService catService;

	@MockBean
	CategoryRepository catRepo;

	private Category c;
	
	@BeforeEach
	public void setUp() {
		c = new Category();
	}
	
	@Test
	void contextLoads() throws Exception {

//		Mockito.when(catRepo.findAll()).thenReturn(
//				Collections.emptyList()
//				);
//		
//		MvcResult mvcResult = mockMvc.perform(
//				MockMvcRequestBuilders.get("/categories/")
//				.accept(MediaType.APPLICATION_JSON_VALUE)
//				).andReturn();
//		
//		System.out.println(mvcResult.getResponse());
//		
//		verify(catRepo).findAll();
	}

	@Test
	public void testListCategories() {
		when(catRepo.findAll()).thenReturn(
				Stream.of(new Category()).collect(Collectors.toList()));
		assertEquals(1, catService.getAllDatas().size());
	}
	
	@Test
	public void testFindCategoryByNameExists() {
		String name = "Thet";
		c = new Category();
		c = catService.findByName(name);
		System.out.println(c);
		when(catRepo.findByName(name)).thenReturn(new Category());
		assertEquals(name, c.getName());
	}
	
	@Test
	public void testSaveNewCategory() {
		Category c = new Category();
		c.setName("test");
		c.setCreated_date(System.currentTimeMillis());
		c.setUpdated_date(System.currentTimeMillis());
		when(catRepo.save(c)).thenReturn(c);
		assertEquals(c, catService.saveData(c));
	}
}
