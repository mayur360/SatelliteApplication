package com.satellite.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.stereotype.Component;

import com.satellite.model.AppointmentModel;

@Component
public class SatelliteItemReader implements ItemReader<AppointmentModel> {

	@Override
	public AppointmentModel read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		DefaultLineMapper<AppointmentModel> defaultLineMapper = new DefaultLineMapper<AppointmentModel>();
		
		return null;
	}

}
