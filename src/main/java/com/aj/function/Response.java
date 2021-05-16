package com.aj.function;

import java.time.LocalDateTime;

public class Response {

	private String driveGearId;
	private LocalDateTime timestamp;
	private String temperature;
	private String status;

	public Response(String driveGearId, LocalDateTime timestamp, String temperature, String status) {
		this.driveGearId = driveGearId;
		this.timestamp = timestamp;
		this.temperature = temperature;
		this.status = status;
	}
}
