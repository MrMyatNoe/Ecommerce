package com.demo.ecom.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.demo.ecom.entity.Category;
import com.demo.ecom.exception.AlreadyExistsException;
import com.demo.ecom.repository.CategoryRepository;

/**
 * @author tmn
 *
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

	@Mock
	CategoryRepository repo;
	CategoryServiceImpl impl;

	@BeforeEach
	void setUp() throws Exception {
		impl = new CategoryServiceImpl(repo);
	}

	@Test
	void canGetAllCategories() {
		// WHEN
		impl.getAllDatas();
		// THEN
		verify(repo).findAll();
	}

	@Test
	void canAddCategory() {
		// GIVEN
		Category c = new Category();
		c.setId(8);
		c.setName("aye");
		c.setCreated_date(System.currentTimeMillis());
		c.setUpdated_date(System.currentTimeMillis());

		// WHEN
		impl.saveData(c);

		// THEN
		ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
		verify(repo).save(categoryArgumentCaptor.capture());

		Category capturedCategory = categoryArgumentCaptor.getValue();
		assertThat(capturedCategory).isEqualTo(c);
	}

	@Test
	void shouldThrowCategoryExists() {
		Category c = new Category();
		c.setId(8);
		c.setName("aye");
		c.setCreated_date(System.currentTimeMillis());
		c.setUpdated_date(System.currentTimeMillis());
		
		given(impl.existByName(c.getName())).willReturn(true);
		// WHEN
		// THEN
		assertThatThrownBy(() -> impl.saveData(c)).isInstanceOf(AlreadyExistsException.class)
				.hasMessageContaining("Category already exists");
	}

	@Test
	@Disabled
	void deleteCategory() {

	}

}
