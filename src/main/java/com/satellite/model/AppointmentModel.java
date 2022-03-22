package com.satellite.model;

import java.util.Date;

import lombok.Data;

@Data
public class AppointmentModel {
	
	private Long applicationNumber;
	
	private Long appointmentId;
	
	private Long personalId;
	
	private String patientFirstName;
	
	private String patientLastName;
	
	private Date appointmentTime;
	
	private String appointmentState;
	
	private Long reasonId;
	
	private Long providerId;
	
	private String location;
	
	private String resource;
	
	private String email;
	
	private String ContactNumber;
	
	private String centralStatus;

}
