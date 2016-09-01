package com.loader.cache;

import java.util.List;

/**
 * Interface for Cache loader, defines methods to be implemented
 * @author padhy
 *
 */
public interface CacheLoader {
	
	public void push(String key, String value);
	public void pushToken(String key);
	public void popToken();
	public String pop(String key);
	public String pop();
	public long getCount();
	public String get(String key);
	public List<String> getAll();
}
