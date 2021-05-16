package com.aj.function;

import java.time.LocalDateTime;

public class Reading {

	private String driveGearId;
	private LocalDateTime timestamp;
	private String temperature;

	public Reading() {
	}

	public String getDriveGearId() {
		return driveGearId;
	}

	public void setDriveGearId(String driveGearId) {
		this.driveGearId = driveGearId;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
}
