package com.satellite.writter;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.satellite.model.AppointmentModel;

@Component
public class SatelliteItemWritter implements ItemWriter<AppointmentModel> {

	@Override
	public void write(List<? extends AppointmentModel> items) throws Exception {
		System.out.println("Inside Item Writer");
		items.stream().forEach(System.out::println);
	}

}
