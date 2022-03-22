package com.satellite.listener;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.satellite.model.SatelliteRequestModel;

@Component
public class SatelliteJobListener implements JobExecutionListener {

	private static final Logger LOGGER = LogManager.getLogger(SatelliteJobListener.class);

	@Autowired
	private Environment env;

	@Override
	public void beforeJob(JobExecution jobExecution) {
		LOGGER.info("beforeJob");
		List<SatelliteRequestModel> requestList = new ArrayList<SatelliteRequestModel>();
		jobExecution.getExecutionContext().put(env.getProperty("REQUEST_LIST"), requestList);
		LOGGER.info("beforeJob populated context");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		jobExecution.getExecutionContext().remove(env.getProperty("REQUEST_LIST"));

	}

}
