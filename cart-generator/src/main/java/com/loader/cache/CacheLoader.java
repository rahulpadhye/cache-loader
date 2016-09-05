package com.loader.cache;

/**
 * Interface for Cache loader, defines methods to be implemented
 * @author padhy
 *
 */
public interface CacheLoader {
	
	public void setCart(String token, String value);
	public String getCart(String token);
	public void pushToken(String key);
	public String popToken();
	public long getTokenCount();
	public long getCartCount();
}
