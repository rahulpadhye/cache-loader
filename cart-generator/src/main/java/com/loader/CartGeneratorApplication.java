package com.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loader.cache.CacheLoader;

@RestController
@SpringBootApplication
public class CartGeneratorApplication {

	@Autowired
	private CacheLoader loader;
	
	public static void main(String[] args) {
		SpringApplication.run(CartGeneratorApplication.class, args);
	}
	
	@PostMapping("/push/cart/{token}")
	void pushCart(@PathVariable String token, @RequestBody String cartJson){
		loader.pushToken(token);
		loader.setCart(token, cartJson);
	}
	
	@RequestMapping("/pop/cart/{token}")
	String popCart(){
		String token = loader.popToken();
		String cartJson = loader.getCart(token);
		loader.removeEntry(token);
		return cartJson;
	}
	
	@PostMapping("/cart/{token}")
	void setCart(@PathVariable String token, @RequestBody String cartJson){
		loader.setCart(token, cartJson);
	}

	@RequestMapping("/cart/{token}")
	String getCart(@PathVariable String token){
		return loader.getCart(token);
	}
	
	@RequestMapping("/push/{token}")
	public void pushToken(@PathVariable String token){
		loader.pushToken(token);
	}
	
	@RequestMapping("/pop")
	public String popToken(){
		return loader.popToken();
	}
	
	@RequestMapping("/tokens")
	long getTokenCount(){
		return loader.getTokenCount();
	}
	
}
