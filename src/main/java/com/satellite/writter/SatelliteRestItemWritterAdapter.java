package com.satellite.writter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.satellite.model.SatelliteRequestModel;
import com.satellite.service.SatelliteRestAPICallService;

@Component
public class SatelliteRestItemWritterAdapter {
	
private static final Logger LOGGER = LogManager.getLogger(SatelliteItemWriterAdapter.class);
	
	@Autowired
	private SatelliteRestAPICallService restAPICallService;
	
	public ItemWriterAdapter<JobExecution> getItemWriterAdapter(){
		LOGGER.info("Inside writer adapter");
		ItemWriterAdapter<JobExecution> itemWriterAdapter = new ItemWriterAdapter<>();
		
		itemWriterAdapter.setTargetObject(restAPICallService);
		itemWriterAdapter.setTargetMethod("callCentralService");
		
		return itemWriterAdapter; 
	}

}
