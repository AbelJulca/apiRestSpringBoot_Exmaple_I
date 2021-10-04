package com.example.product.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.dao.ProductsDAO;
import com.example.product.entitys.Product;

@RestController
@RequestMapping("/products")
public class ProductsREST {
	
	@Autowired	
	private ProductsDAO productsDAO;
	
	@GetMapping
	public ResponseEntity<List<Product>> getProduct(){
		List<Product> products = productsDAO.findAll();
		//return null;
		return ResponseEntity.ok(products);
	}
	
	@RequestMapping(value="{productId}") //products/{productId} -> products/1
	public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId){
		Optional<Product> optionalProduct = productsDAO.findById(productId);
		if (optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.get());
		}else {
			return ResponseEntity.noContent().build();
		}		
	}
	
	@PostMapping
	public ResponseEntity<Product> createproduct(@RequestBody Product product){
		Product newProduct = productsDAO.save(product);	
		return ResponseEntity.ok(newProduct);				
	}
	
	@DeleteMapping(value="{productId}") //products/	(DELETE)
	public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId){
		productsDAO.deleteById(productId);
		return ResponseEntity.ok(null);		
	}
	
	@PutMapping
	public ResponseEntity<Product> updateproduct(@RequestBody Product product){
		Optional<Product> optionalProduct = productsDAO.findById(product.getId());
		if (optionalProduct.isPresent()) {
			Product updateproduct = optionalProduct.get();
			updateproduct.setName(product.getName());
			productsDAO.save(updateproduct);
			return ResponseEntity.ok(updateproduct);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//@GetMapping //localhost:8080
	//@RequestMapping(value="hello",method = RequestMethod.GET)
	public String hello() {
		return "Hello World";
	}
}
