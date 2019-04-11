package com.checkpoint.wifiproject.modules;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.checkpoint.wifiproject.models.DeviceModel;

@Entity
@Table(name = "WIFINETWORK")
public class WifiNetworkEntity {

	@Id
	@Column
	private String id;

	@Column
	private String auth;

	@Column
	private float avgThroughput;

	@OneToMany(mappedBy = "wifinetwork")
	private List<DeviceEntity> devices;

	public WifiNetworkEntity() {

	}

	public WifiNetworkEntity(String id, String auth) {
		this.id = id;
		this.auth = auth;
		this.avgThroughput = 0;
		this.devices = new ArrayList<DeviceEntity>();

	}

	public WifiNetworkEntity(String id, String auth, float avgThroughput, ArrayList<DeviceEntity> devices) {
		super();
		this.id = id;
		this.auth = auth;
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

	public List<DeviceEntity> getDevices() {
		return devices;
	}

	public List<DeviceModel> getDeviceModels() {
		List<DeviceModel> list = new ArrayList();
		for (int i = 0; i < this.devices.size(); i++) {
			DeviceModel device = new DeviceModel(this.devices.get(i).getId());
			list.add(device);
		}
		return list;

	}

	public void setDevices(List<DeviceEntity> devices) {
		this.devices = devices;
	}

	public void addDevice(DeviceEntity device) {
		this.devices.add(device);
	}

	public void updateThroughput() {
		float sum = 0;
		for (int i = 0; i < this.devices.size(); i++) {
			sum += this.devices.get(i).getThroughput();
		}
		this.avgThroughput = sum / (this.devices.size());

	}

}
