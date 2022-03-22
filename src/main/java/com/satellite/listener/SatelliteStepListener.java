package com.satellite.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

import com.satellite.model.AppointmentModel;
import com.satellite.model.SatelliteRequestModel;

@Component
public class SatelliteStepListener implements ItemProcessListener<AppointmentModel, SatelliteRequestModel> {

	private static final Logger LOGGER = LogManager.getLogger(SatelliteStepListener.class);

	@Override
	public void beforeProcess(AppointmentModel item) {

	}

	@Override
	public void afterProcess(AppointmentModel item, SatelliteRequestModel result) {
		LOGGER.info("******* After Process **********");
	}

	@Override
	public void onProcessError(AppointmentModel item, Exception e) {
		// TODO Auto-generated method stub

	}

}
