package com.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	/**
	 * This method pushes cart json, specified in body, to a stack using the token, in path param, as key. The token is also pushed to a separate stack.
	 * @param token
	 * @param cartJson
	 */
	@RequestMapping("/push/cart/{token}")
	void pushCart(@PathVariable String token, @RequestBody String cartJson){
		loader.pushToken(token);
		loader.setCart(token, cartJson);
	}
	
	/**
	 * This method pops a cart JSON from the stack for the provided token
	 * @param token
	 * @return
	 */
	@RequestMapping("/pop/cart/{token}")
	String popCart(@PathVariable String token){
		//String token = loader.popToken();
		String cartJson = loader.getCart(token);
		loader.removeEntry(token);
		return cartJson;
	}
	
	@PostMapping("/cart/{token}")
	void setCart(@PathVariable String token, @RequestBody String cartJson){
		loader.setCart(token, cartJson);
	}

	/**
	 * This method returns the cart json when a token is specified. If none exist then NULL is returned. 
	 * 
	 * @param token
	 * @return Cart JSON
	 */
	@GetMapping("/cart/{token}")
	String getCart(@PathVariable String token){
		return loader.getCart(token);
	}
	
	/**
	 * Push token to a Stack
	 * @param token
	 */
	@RequestMapping("/push/{token}")
	public void pushToken(@PathVariable String token){
		loader.pushToken(token);
	}
	
	/**
	 * THis method should be called to pop the token which is key to the Cart JSON
	 * @return
	 */
	@RequestMapping("/pop/token")
	public String popToken(){
		return loader.popToken();
	}
	
	@RequestMapping("/tokens")
	long getTokenCount(){
		return loader.getTokenCount();
	}
	
}
