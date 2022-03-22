package com.satellite.writter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.satellite.model.SatelliteRequestModel;
import com.satellite.service.RestAPICallService;

@Component
public class SatelliteItemWriterAdapter {

	private static final Logger LOGGER = LogManager.getLogger(SatelliteItemWriterAdapter.class);
	
	@Autowired
	private RestAPICallService restAPICallService;
	
	public ItemWriterAdapter<SatelliteRequestModel> getItemWriterAdapter(){
		LOGGER.info("Inside writer adapter");
		ItemWriterAdapter<SatelliteRequestModel> itemWriterAdapter = new ItemWriterAdapter<>();
		
		itemWriterAdapter.setTargetObject(restAPICallService);
		itemWriterAdapter.setTargetMethod("callCentralService");
		
		return itemWriterAdapter; 
	}
	
}
