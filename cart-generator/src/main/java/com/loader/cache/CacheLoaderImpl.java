/**
 * 
 */
package com.loader.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @author padhy
 *
 */
public class CacheLoaderImpl implements CacheLoader {

	@Autowired
	private StringRedisTemplate redisTemplate;
	private String tokenKeyName = "tokenList";
	private String cartValueKeyName = "cartList";
	ListOperations<String, String> listOps = redisTemplate.opsForList();
	SetOperations<String, String> setOps = redisTemplate.opsForSet();
	ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
	
	
	public void pushToken(String key){
		setOps.add(key);
	}
	
	@Override
	public void push(String key, String value) {
			
		if(!this.redisTemplate.hasKey(tokenKeyName)){
			this.setOps.add(key);
		}
		
		if(!this.redisTemplate.hasKey(cartValueKeyName)){
			this.listOps.rightPush(key, value);
		}
		System.out.println("Value added: " + this.redisTemplate.opsForValue().get(key));
	}

	
	@Override
	public String pop(String key) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.loader.cache.CacheLoader#getCount()
	 */
	@Override
	public long getCount() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.loader.cache.CacheLoader#remove(java.lang.String)
	 */
	@Override
	public String get(String key) {
		return this.redisTemplate.opsForValue().get(key);

	}


	@Override
	public String pop() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void popToken() {
		// TODO Auto-generated method stub
		
	}

}
