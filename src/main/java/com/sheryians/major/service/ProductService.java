package com.sheryians.major.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheryians.major.model.Product;
import com.sheryians.major.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;

	public List<Product> getAllProduct() {
		return repository.findAll();
	}

	public void addProduct(Product product) {
		repository.save(product);

	}

	public void removeProductById(Long id) {
		repository.deleteById(id);
	}

	public Optional<Product> getProductById(Long id) {
		return repository.findById(id);
	}
public List<Product>getAllProductByCategoryid(Long id){
	return repository.findAllProductByCategory_id(id);
}
}
