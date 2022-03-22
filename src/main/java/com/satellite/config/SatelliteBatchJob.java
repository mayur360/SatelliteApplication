package com.satellite.config;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.satellite.listener.SatelliteJobListener;
import com.satellite.listener.SatelliteRestItemProcessorListner;
import com.satellite.listener.SatelliteStepListener;
import com.satellite.model.AppointmentModel;
import com.satellite.model.AppointmentResponse;
import com.satellite.model.SatelliteRequestModel;
import com.satellite.processor.SatelliteItemProcessor;
import com.satellite.processor.SatelliteRestItemProcessor;
import com.satellite.reader.SatelliteFlatFileItemReader;
import com.satellite.reader.SatelliteItemReader;
import com.satellite.writter.SatelliteItemWriterAdapter;
import com.satellite.writter.SatelliteItemWritter;
import com.satellite.writter.SatelliteRestItemWritterAdapter;

@Configuration
public class SatelliteBatchJob {

	private static final Logger LOGGER = LogManager.getLogger(SatelliteBatchJob.class);
	ArrayList<AppointmentModel> appointmentList = new ArrayList<AppointmentModel>();

	@Autowired
	private Environment env;

	@Autowired
	private JobBuilderFactory jobBuilderfactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private SatelliteFlatFileItemReader satelliteFlatFileItemReader;

	@Autowired
	private SatelliteItemReader satelliteItemReader;

	@Autowired
	private SatelliteItemProcessor satelliteItemProcessor;

	@Autowired
	private SatelliteItemWritter SatelliteItemWritter;

	@Autowired
	private SatelliteStepListener satelliteStepListener;

	@Autowired
	private SatelliteItemWriterAdapter satelliteItemWriterAdapter;

	@Autowired
	private SatelliteJobListener satelliteJobListener;

	@Autowired
	private SatelliteRestItemProcessor satelliteRestItemProcessor;

	@Autowired
	private SatelliteRestItemProcessorListner satelliteRestItemProcessorListner;

	@Autowired
	private SatelliteRestItemWritterAdapter satelliteRestItemWritterAdapter;

	@Bean
	public ArrayList<SatelliteRequestModel> getRequestList() {
		return new ArrayList<>();
	}

	@Bean
	public Job readCSVToRestJob() {
		return jobBuilderfactory.get("Get CSV data").incrementer(new RunIdIncrementer()).start(readCSVToRestStep())
				.listener(satelliteJobListener).build();
	}

	public Step readCSVToRestStep() {
		return stepBuilderFactory.get("Read Data from CSV").<AppointmentModel, JobExecution>chunk(2)
				.reader(satelliteFlatFileItemReader.flatFileItemReader()).processor(satelliteRestItemProcessor)
				.writer(satelliteRestItemWritterAdapter.getItemWriterAdapter()).faultTolerant().skip(Throwable.class)
				.retryLimit(10).retry(Throwable.class).build();
	}

}
