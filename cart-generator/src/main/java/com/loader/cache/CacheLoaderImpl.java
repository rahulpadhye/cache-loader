/**
 * 
 */
package com.loader.cache;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @author padhy
 *
 */
@Component
public class CacheLoaderImpl implements CacheLoader {

	private static final Log LOG = LogFactory.getLog(CacheLoaderImpl.class);
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	// This is they REDIS KEY against which the list of tokens is maintained.
	final String listKey = "spring.boot.redis.carts"; 
	
	@Resource(name="redisTemplate")
	private ListOperations<String, String> listOps;
	
	@Resource(name="redisTemplate")
	private ValueOperations<String, String> valueOps;
	
	@Override
	public void pushToken(String token){
		// All tokens are added at the end of the list
		listOps.rightPush(listKey, token);
	}
	
	@Override
	public void setCart(String token, String cartJson) {
		// Set Cart JSON against the specified token
		if(!this.redisTemplate.hasKey(token)){
			valueOps.set(token, cartJson);
		}
	}

	@Override
	public String getCart(String token){
		// Fetch Cart JSON using specified token
		if(!this.redisTemplate.hasKey(token)){
			return valueOps.get(token);
		} else{
			return null;
		}
	}
	
	
	public void removeEntry(String key){
		if(!this.redisTemplate.hasKey(key)){
			redisTemplate.delete(key);
		}
	}
	
	@Override
	public String popToken() {
		// Token is popped from the start of the list which is looked up using KEY.
		if(redisTemplate.hasKey(listKey)){
			return listOps.leftPop(listKey);
		} else{
			return null;
		}
	}
	
	@Override
	public long getTokenCount(){
		return listOps.size(listKey);
	}
	
	@Override
	public long getCartCount(){
		return valueOps.size("");
	}
}
