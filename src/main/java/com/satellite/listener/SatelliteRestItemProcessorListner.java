package com.satellite.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.JobExecution;
import org.springframework.stereotype.Component;

import com.satellite.model.AppointmentModel;

@Component
public class SatelliteRestItemProcessorListner implements ItemProcessListener<AppointmentModel, JobExecution> {

	private static final Logger LOGGER = LogManager.getLogger(SatelliteRestItemProcessorListner.class);

	@Override
	public void beforeProcess(AppointmentModel item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterProcess(AppointmentModel item, JobExecution result) {
		LOGGER.info("******* afterProcess ********" + result.getExecutionContext());
	}

	@Override
	public void onProcessError(AppointmentModel item, Exception e) {
		// TODO Auto-generated method stub

	}

}
