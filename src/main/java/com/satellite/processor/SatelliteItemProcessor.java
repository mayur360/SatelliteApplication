package com.satellite.processor;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.satellite.model.AppointmentModel;
import com.satellite.model.SatelliteRequestModel;

@Component
public class SatelliteItemProcessor implements ItemProcessor<AppointmentModel, SatelliteRequestModel> {

	private static final Logger LOGGER = LogManager.getLogger(SatelliteItemProcessor.class);

	@Autowired
	private Environment env;

	private JobExecution jobExecution;

	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		jobExecution = stepExecution.getJobExecution();
	}

	@Override
	public SatelliteRequestModel process(AppointmentModel item) throws Exception {
		LOGGER.info("Inside Processor");
		List<SatelliteRequestModel> requestList = (ArrayList<SatelliteRequestModel>) jobExecution.getExecutionContext()
				.get(env.getProperty("REQUEST_LIST"));
		SatelliteRequestModel request = new SatelliteRequestModel();
		convertModelToRequest(item, request);
		requestList.add(request);
		jobExecution.getExecutionContext().put(env.getProperty("REQUEST_LIST"), requestList);
		return request;
	}

	private void convertModelToRequest(AppointmentModel item, SatelliteRequestModel request) {
		request.setAppointmentId(item.getAppointmentId());
		request.setPersonalId(item.getPersonalId());
		request.setAppointmentTime(item.getAppointmentTime());
		request.setAppointmentState(item.getAppointmentState());
		request.setReasonId(item.getReasonId());
		request.setProviderId(item.getProviderId());
		request.setLocation(item.getLocation());
		request.setResource(item.getResource());
		request.setFirstName(item.getPatientFirstName());
		request.setLastName(item.getPatientLastName());
		request.setEmail(item.getEmail());
		request.setContactNumber(item.getContactNumber());
	}

}
