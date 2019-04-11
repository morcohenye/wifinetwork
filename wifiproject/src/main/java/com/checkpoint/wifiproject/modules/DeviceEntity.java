package com.checkpoint.wifiproject.modules;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.checkpoint.wifiproject.models.DeviceToNetworkdModel;

@Entity
@Table(name = "DEVICE")
public class DeviceEntity {
	@Id
	private String id;
	@Column
	private float throughput;
//	@Column
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "WIFINETWORK_ID")
	private WifiNetworkEntity wifinetwork;

	public DeviceEntity() {
		super();
	}

	public DeviceEntity(DeviceToNetworkdModel deviceToNetwork, WifiNetworkEntity wifiNetwork) {
		this.id = deviceToNetwork.getDeviceId();
		this.throughput = 0;
		this.wifinetwork = wifiNetwork;
	}

	public DeviceEntity(String id, WifiNetworkEntity wifiNetwork) {
		super();
		this.id = id;
		this.throughput = 0;
		this.wifinetwork = wifiNetwork;
	}

	public DeviceEntity(String id, float throughput, WifiNetworkEntity wifiNetwork) {
		super();
		this.id = id;
		this.throughput = throughput;
		this.wifinetwork = wifiNetwork;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getThroughput() {
		return throughput;
	}

	public void setThroughput(float throughput) {
		this.throughput = throughput;
	}

	public WifiNetworkEntity getConnectedToNetwork() {
		return wifinetwork;
	}

	public void setConnectedToNetwork(WifiNetworkEntity connectedToNetwork) {
		this.wifinetwork = connectedToNetwork;
	}

}
