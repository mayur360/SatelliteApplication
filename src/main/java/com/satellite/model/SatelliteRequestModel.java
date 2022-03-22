package com.satellite.model;

import java.util.Date;
import lombok.Data;

@Data
public class SatelliteRequestModel {

	private Long appointmentId;
	private Long personalId;
	private Date appointmentTime;
	private String appointmentState;
	private Long reasonId;
	private Long providerId;
	private String location;
	private String resource;
	private String firstName;
	private String LastName;
	private String email;
	private String contactNumber;
}
