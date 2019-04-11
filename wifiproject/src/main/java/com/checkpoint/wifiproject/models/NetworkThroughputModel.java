package com.checkpoint.wifiproject.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NetworkThroughputModel {

	@JsonProperty("device_id")
	private String deviceId;
	@JsonProperty("network_id")
	private String networkId;
	private float throughput;

	public NetworkThroughputModel() {
		super();
	}

	public NetworkThroughputModel(String deviceId, String networkId, float throughput) {
		super();
		this.deviceId = deviceId;
		this.networkId = networkId;
		this.throughput = throughput;
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

	public float getThroughput() {
		return throughput;
	}

	public void setThroughput(float throughput) {
		this.throughput = throughput;
	}

}
