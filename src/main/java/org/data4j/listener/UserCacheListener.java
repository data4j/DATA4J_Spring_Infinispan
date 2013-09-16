package org.data4j.listener;

import org.apache.log4j.Logger;
import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryRemovedEvent;

/**
 * 
 * UserCacheListener Class listens the changes made on the cache
 * 
 * @author data4j.org
 * @since 16 Sept 2013
 * @version 1.0.0
 *
 */
@Listener
public class UserCacheListener {

   private static final Logger log = Logger.getLogger(UserCacheListener.class);

   /**
    * Listens the entry creation notifications
    *
    * @param CacheEntryCreatedEvent<?, ?> entry creation event
    */
   @CacheEntryCreated
   public void cacheEntryCreated(CacheEntryCreatedEvent<?, ?> event) {
      if (!event.isPre()) {
    	  log.debug("Created Entry key : " + event.getKey() +", cache : " + event.getCache());
      }         
   }

   /**
    * Listens the entry removing notifications
    *
    * @param CacheEntryRemovedEvent<?, ?> entry removing event
    */
   @CacheEntryRemoved
   public void cacheEntryRemoved(CacheEntryRemovedEvent<?, ?> event) {
	   if (!event.isPre()) {
		   log.debug("Removed Entry key : " + event.getKey() +", cache : " + event.getCache());
	   }
   }
   
   /**
    * Listens the entry modification notifications
    *
    * @param CacheEntryRemovedEvent<?, ?> entry modification event
    */
   @CacheEntryModified
   public void cacheEntryModified(CacheEntryModifiedEvent<?, ?> event) {
//	   if (!event.isPre()) {
//		   log.debug("Modified Entry key :" + event.getKey() +", value : " + event.getCache());
//	   }
   }

}
