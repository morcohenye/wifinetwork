package com.checkpoint.wifiproject.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceToNetworkdModel {

	@JsonProperty("device_id")
	private String deviceId;
	@JsonProperty("network_id")
	private String networkId;
	private String auth;

	public DeviceToNetworkdModel() {
		super();
	}

	public DeviceToNetworkdModel(String networkId) {
		this.networkId = networkId;

	}

	public DeviceToNetworkdModel(String deviceId, String networkId, String auth) {
		this.deviceId = deviceId;
		this.networkId = networkId;
		this.auth = auth;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

}
