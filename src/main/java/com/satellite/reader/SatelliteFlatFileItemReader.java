package com.satellite.reader;

import java.io.File;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.satellite.model.AppointmentModel;

@Component
public class SatelliteFlatFileItemReader {

	@Autowired
	private Environment env;

	@Qualifier("readCSVStep")
	@StepScope
	public FlatFileItemReader<AppointmentModel> flatFileItemReader() {

		FlatFileItemReader<AppointmentModel> flatFileItemReader = new FlatFileItemReader<AppointmentModel>();
		flatFileItemReader.setResource(new FileSystemResource(new File(env.getProperty("CSVFilePath"))));

		DefaultLineMapper<AppointmentModel> defaultLineMapper = new DefaultLineMapper<AppointmentModel>();
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();

		delimitedLineTokenizer.setNames(env.getProperty("ApplicationNumber"), env.getProperty("AppointmentId"),
				env.getProperty("PersonalId"), env.getProperty("AppointmentTime"), env.getProperty("PatientFirstName"),
				env.getProperty("PatientLastName"), env.getProperty("AppointmentState"), env.getProperty("ReasonID"),
				env.getProperty("Location"), env.getProperty("Resource"), env.getProperty("Email"),
				env.getProperty("ContactNumber"), env.getProperty("ProviderId"), env.getProperty("CentralStatus"));

		BeanWrapperFieldSetMapper<AppointmentModel> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(AppointmentModel.class);

		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

		flatFileItemReader.setLineMapper(defaultLineMapper);
		flatFileItemReader.setLinesToSkip(1);

		return flatFileItemReader;

	}

}
