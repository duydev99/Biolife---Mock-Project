package com.jsfw.services.impl.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
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

import com.jsfw.models.Tbl_Image_Product;
import com.jsfw.models.Tbl_Product;
import com.jsfw.repositories.ImageRepository;
import com.jsfw.services.impl.ImageServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ImageServiceImplTest {
	@Mock
	ImageRepository repository;
	
	@InjectMocks
	ImageServiceImpl serviceImpl;
	

	@Test
	void test_findById() {

		Optional<Tbl_Image_Product> image = Optional.of(new Tbl_Image_Product("image"));
		
		when(repository.findById(1)).thenReturn(image);
		
		Optional<Tbl_Image_Product> image2 = serviceImpl.findById(1);
		
		assertThat(image2).isEqualTo(image);

	}
	@Test
	void test_create() {
		Tbl_Image_Product image = new Tbl_Image_Product("image");

		when(repository.save(image)).thenReturn(image);

		Tbl_Image_Product savedImage = repository.save(image);
		assertThat(savedImage.getId()).isNotNull();
	}
	@Test
	void test_edit() {
		Tbl_Image_Product image1 = new Tbl_Image_Product("image");
		when(repository.save(image1)).thenReturn(image1);

		Tbl_Image_Product savedImage = repository.save(image1);
		Tbl_Image_Product image2 = new Tbl_Image_Product("image update");


	    // providing knowledge
	    when(repository.save(image2)).thenReturn(image2);
	    Tbl_Image_Product editImage = repository.save(image2);
	    assertThat(savedImage.getSource().equals(editImage.getSource()));

	}
	@Test
	void test_remove() {
		Tbl_Image_Product image = new Tbl_Image_Product(1,"image"); 
		doNothing().when(repository).deleteById(image.getId());
		 
		serviceImpl.remove(image.getId());
		verify(repository,times(1)).deleteById(image.getId());
		
	}
	@Test
	void test_findByProduct() {
		List<Tbl_Image_Product> image = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			image.add(new Tbl_Image_Product("image " + i));
		}
		Optional<Tbl_Product> product = Optional.of(new Tbl_Product(1, "product"));
		when(repository.findByTblProduct(product)).thenReturn(image);
		List<Tbl_Image_Product> image2 = serviceImpl.findByProduct(product);
		assertThat(image2).isNotNull();
	}
}
