package org.data4j.exe;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.data4j.srv.IUserCacheService;
import org.data4j.user.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CacheUpdater Class updates cache and logs entries...
 * 
 * @author data4j.org
 * @since 16 Sept 2013
 * @version 1.0.0
 *
 */
@Component("cacheUpdater")
public class CacheUpdater implements Runnable, BeanFactoryAware {
	
	private static final Logger log = Logger.getLogger(CacheUpdater.class);
	
	@Autowired
	private IUserCacheService userCacheService;
	
	private BeanFactory beanFactory;
	
	/**
     * Adds entries to the cache and logs entries...
     *
     */
	public void run() {		
		
		//New Users are created for the first member of the cluster... 
		User firstUser = createUser();
		firstUser.setId(1);
		firstUser.setName("Bruce");
		firstUser.setSurname("Willis");
		
		User secondUser = createUser();
		secondUser.setId(2);
		secondUser.setName("Clint");
		secondUser.setSurname("Eastwood");
		
		//First Entry is added to user-cache...
		userCacheService.addToUserCache(firstUser.getId(), firstUser);
		
		//Second Entry is added to user-cache...
		userCacheService.addToUserCache(secondUser.getId(), secondUser);

		//Cache Entries are printed...
		printCacheEntries();		
	}
	
	/**
     * Prints entries of user-cache...
     *
     */
	private void printCacheEntries() {
		Collection<User> userCollection = null;
		try {
			while(true) {
				userCollection = (Collection<User>)userCacheService.getUserCache().values();
				for(User user : userCollection) {
					log.debug("User Cache Content on first Member : " + user);
				}
				Thread.sleep(30000);			
			}
		} catch (InterruptedException e) {
			log.error(e);
		}
	}
	
	/**
     * Creates new user
     *
     * @return User user
     */
	private User createUser() {
		return beanFactory.getBean(User.class);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
		
}
