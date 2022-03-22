package com.satellite.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.SkipListener;

import com.satellite.model.AppointmentModel;

public class SkipListnerImpl implements SkipListener<AppointmentModel, JobExecution> {

	@Override
	public void onSkipInRead(Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSkipInWrite(JobExecution item, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSkipInProcess(AppointmentModel item, Throwable t) {
		// TODO Auto-generated method stub
		
	}

}
