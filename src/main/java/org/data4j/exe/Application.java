package org.data4j.exe;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * This class starts the application
 * 
 * @author data4j.org
 * @since 16 Sept 2013
 * @version 1.0.0
 *
 */
public class Application {

	/**
     * Starts the application
     *
     * @param  String[] args
     *
     */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CacheUpdater cacheUpdater = (CacheUpdater) context.getBean(CacheUpdater.class);
		Thread cacheUpdaterThread = new Thread(cacheUpdater);
		cacheUpdaterThread.start();
	}
}
