package com.byob.inject.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

/**
 * Logger injector guice module
 * 
 * @author gpereira
 *
 */
public class LoggerModule extends AbstractModule {

	@Override
	protected void configure() {
		bindListener(Matchers.any(), new SLF4JTypeListener());
	}
	
	public static Logger newLogger (final Class clazz){
		return LoggerFactory.getLogger(clazz);	
	}
	
}
