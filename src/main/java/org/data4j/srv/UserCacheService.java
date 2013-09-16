package org.data4j.srv;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

import org.apache.log4j.Logger;
import org.data4j.listener.UserCacheListener;
import org.data4j.user.User;
import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.springframework.stereotype.Service;

/**
 * UserCacheService Class is implementation of IUserCacheService Interface.
 * 
 * @author data4j.org
 * @since 16 Sept 2013
 * @version 1.0.0
 *
 */
@Service("userCacheService")
public class UserCacheService implements IUserCacheService {
	
	private static final Logger log = Logger.getLogger(UserCacheService.class);
	
	private Cache<Long, User> userCache = null;
	private static final String CONFIG_XML = "infinispan-distribution.xml";
	private static final String USER_CACHE = "user-cache";

	/**
     * Initializes DefaultCacheManager and creates user-cache instance.
     *
     * @param DefaultCacheManager cacheManager
     *
     */
	@PostConstruct
	private void initialize() {
		DefaultCacheManager cacheManager;
		try {
			cacheManager = new DefaultCacheManager(CONFIG_XML);		
			userCache = cacheManager.getCache(USER_CACHE);
			userCache.addListener(new UserCacheListener());	
		} catch (IOException e) {
			log.error(e);
		}
	}	
	
	public void addToUserCache(long id, User user) {
		TransactionManager tm = null;
		try {
			tm = getUserCache().getAdvancedCache().getTransactionManager();
			tm.begin();			
			getUserCache().put(id, user);
			tm.commit();
		} catch (SecurityException | IllegalStateException | NotSupportedException 
				| SystemException | RollbackException | HeuristicMixedException
				| HeuristicRollbackException e) {
			try {
				tm.rollback();
				log.error(e);
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				log.error(e1);
			}
		} 
	}

	public void deleteFromUserCache(long id) {
		TransactionManager tm = null;
		try {
			tm = getUserCache().getAdvancedCache().getTransactionManager();
			tm.begin();
			getUserCache().remove(id);
			tm.commit();			
		} catch (SecurityException | IllegalStateException | RollbackException 
				| HeuristicMixedException | HeuristicRollbackException | NotSupportedException 
				| SystemException e) {
			try {
				tm.rollback();
				log.error(e);
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				log.error(e1);
			}
		} 
	}
	
	public Cache<Long, User> getUserCache() {
		return userCache;
	}

	public void setUserCache(Cache<Long, User> userCache) {
		this.userCache = userCache;
	}
			
}
