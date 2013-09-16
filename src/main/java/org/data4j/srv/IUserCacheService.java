package org.data4j.srv;

import org.data4j.user.User;
import org.infinispan.Cache;


/**
 * IUserCacheService Interface exposes cache functionality.
 * 
 * @author data4j.org
 * @since 16 Sept 2013
 * @version 1.0.0
 *
 */
public interface IUserCacheService {

	/**
     * Adds User entry to cache
     *
     * @param long id - key
     * @param User value 
     *
     */
	void addToUserCache(long id, User user);
	
	/**
     * Removes User entry from cache
     *
     * @param long id - key
     *
     */
	void deleteFromUserCache(long id);
	
	/**
     * Gets User cache
     *
     * @return Cache<Long, User> cache
     */
	Cache<Long, User> getUserCache();
		
}
