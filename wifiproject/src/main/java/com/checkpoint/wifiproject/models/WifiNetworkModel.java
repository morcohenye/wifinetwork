package com.checkpoint.wifiproject.models;

import java.util.List;

import com.checkpoint.wifiproject.modules.WifiNetworkEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WifiNetworkModel {

	@JsonProperty("id")
	private String id;

	@JsonProperty("auth")
	private String auth;

	@JsonProperty("avg_throughput")
	private float avgThroughput;

	@JsonProperty("devices")
	private List<DeviceModel> devices;

	public WifiNetworkModel() {

	}

	public WifiNetworkModel(String id) {
		this.id = id;
	}

	public WifiNetworkModel(WifiNetworkEntity wifiNetworkEnt) {
		this.id = wifiNetworkEnt.getId();
		this.auth = wifiNetworkEnt.getAuth();
		this.avgThroughput = wifiNetworkEnt.getAvgThroughput();
		this.devices = wifiNetworkEnt.getDeviceModels();
	}

	public WifiNetworkModel(String networkId, String networkAuth, float avgThroughput, List<DeviceModel> devices) {
		this.id = networkId;
		this.auth = networkAuth;
		this.avgThroughput = avgThroughput;
		this.devices = devices;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public float getAvgThroughput() {
		return avgThroughput;
	}

	public void setAvgThroughput(float avgThroughput) {
		this.avgThroughput = avgThroughput;
	}

	public List<DeviceModel> getDevices() {
		return this.devices;
	}

	public void addDevice(DeviceModel device) {
		this.devices.add(device);
	}

}
