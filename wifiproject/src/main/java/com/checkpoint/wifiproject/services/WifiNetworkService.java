package com.checkpoint.wifiproject.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checkpoint.wifiproject.models.DeviceToNetworkdModel;
import com.checkpoint.wifiproject.models.NetworkThroughputModel;
import com.checkpoint.wifiproject.modules.DeviceEntity;
import com.checkpoint.wifiproject.modules.WifiNetworkEntity;
import com.checkpoint.wifiproject.repositories.DeviceRepository;
import com.checkpoint.wifiproject.repositories.WifiNetworkRepository;

@Service
public class WifiNetworkService {

	@Autowired
	private WifiNetworkRepository wifiNetworkRepository;
	@Autowired
	private DeviceRepository deviceRepository;

	public WifiNetworkEntity fetchNetwork(String id) {
		Optional<WifiNetworkEntity> wifiNetworkEnt = wifiNetworkRepository.findById(id);
		if (!wifiNetworkEnt.isPresent()) {
			return null;
		}
		WifiNetworkEntity wifiEnt = wifiNetworkEnt.get();
		return wifiEnt;

	}

	public WifiNetworkEntity addDeviceToNetwork(DeviceToNetworkdModel deviceToNetwork) {
		Optional<WifiNetworkEntity> wifiNetworkEntity = wifiNetworkRepository.findById(deviceToNetwork.getNetworkId());
		// if network doesn't exist
		if (!wifiNetworkEntity.isPresent()) {
			// create new network and new device add them to the tables
			WifiNetworkEntity newWifiEnt = new WifiNetworkEntity(deviceToNetwork.getNetworkId(),
					deviceToNetwork.getAuth());
			DeviceEntity newDeviceEnt = new DeviceEntity(deviceToNetwork, newWifiEnt);
			newWifiEnt.addDevice(newDeviceEnt);
			deviceRepository.save(newDeviceEnt);
			wifiNetworkRepository.save(newWifiEnt);
			return newWifiEnt;
		}
		// network exists so add device to network
		WifiNetworkEntity wifiEnt = wifiNetworkEntity.get();
		// if the network found has the same auth as the req
		if (wifiEnt.getAuth().equals(deviceToNetwork.getAuth())) {
			DeviceEntity deviceEnt = new DeviceEntity(deviceToNetwork, wifiEnt);
			wifiEnt.addDevice(deviceEnt);
			deviceRepository.save(deviceEnt);
			wifiNetworkRepository.save(wifiEnt);
			return wifiEnt;
		} else {
			return null;
		}

	}

	public WifiNetworkEntity reportNetworkThroughput(NetworkThroughputModel networkThroughput) {
		Optional<WifiNetworkEntity> wifiNetworkEnt = wifiNetworkRepository.findById(networkThroughput.getNetworkId());
		// check if wifiNetwork exists
		if (!wifiNetworkEnt.isPresent()) {
			// wifiNetwork doesn't exist
			return null;
		}

		Optional<DeviceEntity> dev = deviceRepository.findById(networkThroughput.getDeviceId());
		// wifiNetwork exists. Now check that the device exists
		if (dev.isPresent() && dev.get().getConnectedToNetwork().getId() == networkThroughput.getNetworkId()) {
			// Device exists
			DeviceEntity devEnt = dev.get();
			devEnt.setThroughput(networkThroughput.getThroughput());
			deviceRepository.save(devEnt);
			WifiNetworkEntity wifiEnt = wifiNetworkEnt.get();
			wifiEnt.updateThroughput();
			wifiNetworkRepository.save(wifiEnt);
			return wifiEnt;
		} else {
			return null;
		}

	}

}
