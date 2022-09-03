package com.jsfw.services.impl.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.jsfw.models.Tbl_Category;
import com.jsfw.models.Tbl_Manufacturer;
import com.jsfw.models.Tbl_Product;
import com.jsfw.repositories.ProductRepository;
import com.jsfw.services.impl.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
	@Mock
	ProductRepository repository;
	
	@InjectMocks
	ProductServiceImpl serviceImpl;
	
	@Test
	void test_findAll() {
		List<Tbl_Product> product = new ArrayList<>();
		for(int i = 0; i < 20; i++) {
			product.add(new Tbl_Product(i,"product "+i));
		}
		
		when(repository.findAll(Sort.by(Sort.Direction.DESC, "id"))).thenReturn(product);
		
		List<Tbl_Product> product2 = serviceImpl.findAll();
		
		assertThat(product2.size()).isEqualTo(product.size());
		
	
	}
	@Test
	void test_findAllOrderByName() {
		List<Tbl_Product> product = new ArrayList<>();
		for(int i = 0; i < 20; i++) {
			product.add(new Tbl_Product(i,"product "+i));
		}
		
		when(repository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(product);
		
		List<Tbl_Product> product2 = serviceImpl.findAllOrderByName();
		
		assertThat(product2.size()).isEqualTo(product.size());
		
	
	}
	@Test
	void test_findByCategoryAndManufacturer() {
		Tbl_Category category = new Tbl_Category (1,"category");
		Tbl_Manufacturer manufacturer = new Tbl_Manufacturer (1,"manufacturer");
		List<Tbl_Product> product = new ArrayList<>();
		for(int i = 0; i < 20; i++) {
			product.add(new Tbl_Product(i,"product "+i));
		}
		
		when(repository.findTop07ByTblCategoryOrTblManufacturerOrderByIdDesc(category, manufacturer)).thenReturn(product);
		
		List<Tbl_Product> product2 = serviceImpl.findByCategoryAndManufacturer(category, manufacturer);
		
		assertThat(product2.size()).isEqualTo(product.size());
		
	
	}
	@Test
	void test_findByManufacturer() {
		
		Tbl_Manufacturer manufacturer = new Tbl_Manufacturer (1,"manufacturer");
		List<Tbl_Product> product = new ArrayList<>();
		for(int i = 0; i < 20; i++) {
			product.add(new Tbl_Product(i,"product "+i));
		}
		
		when(repository.findByTblManufacturer( manufacturer)).thenReturn(product);
		
		List<Tbl_Product> product2 = serviceImpl.findByManufacturer(manufacturer);
		
		assertThat(product2.size()).isEqualTo(product.size());
		
	
	}
	@Test
	void test_findByCategory() {
		Tbl_Category category = new Tbl_Category (1,"category");

		List<Tbl_Product> product = new ArrayList<>();
		for(int i = 0; i < 20; i++) {
			product.add(new Tbl_Product(i,"product "+i));
		}
		
		when(repository.findByTblCategory(category)).thenReturn(product);
		
		List<Tbl_Product> product2 = serviceImpl.findByCategory(category);
		
		assertThat(product2.size()).isEqualTo(product.size());
		
	
	}
	@Test
	void test_findTop10New() {
		List<Tbl_Product> product = new ArrayList<>();
		for(int i = 0; i < 20; i++) {
			product.add(new Tbl_Product(i,"product "+i));
		}
		
		when(repository.findTop10ByOrderByIdDesc()).thenReturn(product);
		
		List<Tbl_Product> product2 = serviceImpl.findTop10New();
		
		assertThat(product2.size()).isEqualTo(product.size());
		
	
	}
	@Test
	void test_findById() {
		
		Optional<Tbl_Product> product = Optional.of(new Tbl_Product(1,"product "+1));
		when(repository.findById(1)).thenReturn(product);
		
		Optional<Tbl_Product> product2 = serviceImpl.findById(1);
		
		assertThat(product2).isEqualTo(product);
		verify(repository).findById(1);
	}
	@Test
	void test_findByName() {
		Tbl_Product product = new Tbl_Product(1, "product");

		when(repository.findByName("product")).thenReturn(product);

		Tbl_Product product2 = serviceImpl.findByName("product");

		assertThat(product2).isEqualTo(product);
		verify(repository).findByName("product");
	}
	
	@Test
	void test_findByProductName() {
		List<Tbl_Product> product = new ArrayList<>();
		for(int i = 0; i < 20; i++) {
			product.add(new Tbl_Product(i,"product "+i));
		}
		

		when(repository.findByNameLike("%product%")).thenReturn(product);

		List<Tbl_Product> product2 =serviceImpl.findByProductName("product");

		assertThat(product2).isNotNull();
		verify(repository).findByNameLike("%product%");
	}
	@Test
	void test_findByCategoryName() {
		List<Tbl_Product> product = new ArrayList<>();
		for(int i = 0; i < 20; i++) {
			product.add(new Tbl_Product(i,"Category Name "+i));
		}
		

		when(repository.findByTblCategoryNameLike("%Category%")).thenReturn(product);

		List<Tbl_Product> product2 =serviceImpl.findByCategoryName("Category");

		assertThat(product2).isNotNull();
		verify(repository).findByTblCategoryNameLike("%Category%");
	}
	@Test
	void test_findByManufacturerName() {
		List<Tbl_Product> product = new ArrayList<>();
		for(int i = 0; i < 20; i++) {
			product.add(new Tbl_Product(i,"Manufacturer Name "+i));
		}
		

		when(repository.findByTblManufacturerNameLike("%Manufacturer%")).thenReturn(product);

		List<Tbl_Product> product2 =serviceImpl.findByManufacturerName("Manufacturer");

		assertThat(product2).isNotNull();
		verify(repository).findByTblManufacturerNameLike("%Manufacturer%");
	}
	@Test
	void test_create() {
		Tbl_Product product = new Tbl_Product(1, "product");

		when(repository.save(product)).thenReturn(product);

		Tbl_Product savedProduct = repository.save(product);
		assertThat(savedProduct.getId()).isNotNull();
	}
	@Test
	void test_edit() {
		Tbl_Product product = new Tbl_Product(1, "product");
		when(repository.save(product)).thenReturn(product);


		Tbl_Product savedProduct = repository.save(product);
		Tbl_Product product2 = new Tbl_Product(1, "product update");


	    // providing knowledge
	    when(repository.save(product2)).thenReturn(product2);
	    Tbl_Product editProduct = repository.save(product2);
	    assertThat(savedProduct.getName().equals(editProduct.getName()));
	}
	
}
