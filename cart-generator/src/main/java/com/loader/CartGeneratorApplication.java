package com.loader.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loader.cache.CacheLoader;
import com.loader.cache.CacheLoaderImpl;

@RestController
@SpringBootApplication
public class CartGeneratorApplication {

	CacheLoader loader;
	
	public CartGeneratorApplication() {
	// Use IoC here
		loader = new CacheLoaderImpl();
	}
	
	 
	
	public static void main(String[] args) {
		SpringApplication.run(CartGeneratorApplication.class, args);
	}
	
	@PostMapping("/push/{token}")
	void pushCart(@PathVariable String token, @RequestBody String cartJson){
		loader.push(token, cartJson);
		System.out.println("after invoking push API");
	}

	@RequestMapping("/pop")
	void popCart(@PathVariable String token, @RequestBody String cartJson){
		loader.pop();
		System.out.println("after invoking push API");
	}
	
	
}
