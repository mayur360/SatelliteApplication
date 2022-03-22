package com.satellite.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.satellite.model.AppointmentModel;
import com.satellite.model.AppointmentResponse;
import com.satellite.model.SatelliteRequestModel;

@Service
@Component
public class RestAPICallService {

	private static final Logger LOGGER = LogManager.getLogger(RestAPICallService.class);
	List<AppointmentModel> appointmentList;
	 
	
	public AppointmentResponse callCentralService(SatelliteRequestModel appointment) {
		LOGGER.info("Inside callCentralService");
		RestTemplate restTemplate = new RestTemplate();
		AppointmentResponse response = new AppointmentResponse();
		response = restTemplate.postForObject("http://localhost:8091/central/appointment/create", appointment, AppointmentResponse.class);
		return response;
	}
	
}
