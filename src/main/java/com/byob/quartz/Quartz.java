package com.byob.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.slf4j.Logger;

import com.byob.inject.logger.InjectLogger;
import com.google.inject.Inject;

public class Quartz {
	@InjectLogger
	private Logger log;
	
	private final Scheduler scheduler;
	
	@Inject
	public Quartz(final SchedulerFactory factory,
			final GuiceJobFactory jobFactory) throws SchedulerException {
		scheduler = factory.getScheduler();
		scheduler.setJobFactory(jobFactory);
		scheduler.start();
	}

	public final Scheduler getScheduler() {
		return scheduler;
	}

	public void shutdown() {
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			log.error("Failed to shutdown sheduler.",e);
		}
	}
}
