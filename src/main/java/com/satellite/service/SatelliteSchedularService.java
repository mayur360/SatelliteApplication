package com.satellite.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SatelliteSchedularService {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Qualifier("readCSVToRestJob")
	@Autowired
	private Job readCSVJob;
	
	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	public void getCSVJobSceduler() {
		
		Map<String, JobParameter> params = new HashMap<>();
		params.put("currentTime", new JobParameter(System.currentTimeMillis()));
		
		JobParameters jobParameters = new JobParameters(params);
		JobExecution jobExecution;
		try {
			jobExecution = jobLauncher.run(readCSVJob, jobParameters);
			System.out.println("Job Execution ID = " + jobExecution.getId());
		} catch (JobExecutionAlreadyRunningException jeare) {
			jeare.printStackTrace();
		}catch (JobRestartException jre) {
			jre.printStackTrace();
		}catch (JobInstanceAlreadyCompleteException jiace) {
			jiace.printStackTrace();
		}catch (JobParametersInvalidException jpie) {
			jpie.printStackTrace();
		}
	}

}
