package com.satellite.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.satellite.model.AppointmentModel;
import com.satellite.model.AppointmentResponse;
import com.satellite.model.SatelliteRequestModel;

@Service
@Component
public class SatelliteRestAPICallService {

	private static final Logger LOGGER = LogManager.getLogger(RestAPICallService.class);

	@Autowired
	private Environment env;

	List<AppointmentModel> appointmentList;

	public List<AppointmentResponse> callCentralService(JobExecution jobExecution) throws Exception {
		LOGGER.info("Inside callCentralService");
		List<SatelliteRequestModel> requestList = new ArrayList<SatelliteRequestModel>();
		if (jobExecution != null && jobExecution.getExecutionContext() != null
				&& jobExecution.getExecutionContext().get(env.getProperty("REQUEST_LIST")) != null) {
			requestList = (ArrayList<SatelliteRequestModel>) jobExecution.getExecutionContext()
					.get(env.getProperty("REQUEST_LIST"));
			if (requestList != null && !requestList.isEmpty()) {
				RestTemplate restTemplate = new RestTemplate();
				SatelliteRequestModel appointment = new SatelliteRequestModel();
				List<AppointmentResponse> responseList = new ArrayList<AppointmentResponse>();
				responseList = restTemplate.postForObject("http://localhost:8091/central/appointment/create",
						requestList, ArrayList.class);
				JsonArray jsonArray = new Gson().toJsonTree(responseList).getAsJsonArray();
				jobExecution.getExecutionContext().put(env.getProperty("REQUEST_LIST"),
						new ArrayList<SatelliteRequestModel>());
				updateCSVFiles(jsonArray);
				return responseList;
			}
		}
		return null;
	}

	private void updateCSVFiles(JsonArray jsonArray) {
		LOGGER.info("Inside updateCSVFiles");
		try {
			CSVReader csvReader = new CSVReader(new FileReader(new File(env.getProperty("CSVFilePath"))));
			List<String[]> allCSVData = csvReader.readAll();
			String appointmentId;

			for (JsonElement pa : jsonArray) {
				JsonObject appointmentObj = pa.getAsJsonObject();
				String message = appointmentObj.get(env.getProperty("message")).getAsString();
				String jappointmentId = appointmentObj.get(env.getProperty("appointmentId")).getAsString();
				String centralStatus = appointmentObj.get(env.getProperty("centralStatus")).getAsString();
				for (int i = 1; i < allCSVData.size(); i++) {
					appointmentId = allCSVData.get(i)[1];
					if (appointmentId.equals(jappointmentId)) {
						allCSVData.get(i)[13] = centralStatus;
					}
				}
			}
			CSVWriter csvWriter = new CSVWriter(new FileWriter(new File(env.getProperty("CSVFilePath"))));
			csvWriter.writeAll(allCSVData);
			csvWriter.flush();

		} catch (FileNotFoundException e) {
			LOGGER.info("Check the file location");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}

	}

}
