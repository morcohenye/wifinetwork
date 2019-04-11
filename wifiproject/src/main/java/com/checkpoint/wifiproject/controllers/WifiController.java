package com.checkpoint.wifiproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.checkpoint.wifiproject.models.DeviceToNetworkdModel;
import com.checkpoint.wifiproject.models.NetworkThroughputModel;
import com.checkpoint.wifiproject.models.WifiNetworkModel;
import com.checkpoint.wifiproject.modules.WifiNetworkEntity;
import com.checkpoint.wifiproject.services.CustomError;
import com.checkpoint.wifiproject.services.WifiNetworkService;

@RestController
@RequestMapping(path = "/api/network")
public class WifiController {

	@Autowired
	private WifiNetworkService wifiNetworkService;

	@GetMapping(path = "/", produces = "application/json")
	public ResponseEntity<?> getNetwork(@RequestParam String id) {

		WifiNetworkEntity wifiNetworkEnt = wifiNetworkService.fetchNetwork(id);
		if (wifiNetworkEnt == null) {
			return new ResponseEntity<>(new CustomError("Unable to find network. Network doesn't exist"),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new WifiNetworkModel(wifiNetworkEnt), HttpStatus.OK);

	}

	@PutMapping(path = "/connect", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> connectDevice(@RequestBody DeviceToNetworkdModel deviceToNetwork) {

		WifiNetworkEntity wifiNetworkEnt = wifiNetworkService.addDeviceToNetwork(deviceToNetwork);
		if (wifiNetworkEnt == null) {
			return new ResponseEntity<>(
					new CustomError("Unable to update network. Selected auth is not the same as network auth"),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new WifiNetworkModel(wifiNetworkEnt), HttpStatus.OK);

	}

	@PostMapping(path = "/report", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> reportNetworkThroughput(@RequestBody NetworkThroughputModel networkThroughput) {

		WifiNetworkEntity wifiNetworkEnt = wifiNetworkService.reportNetworkThroughput(networkThroughput);
		if (wifiNetworkEnt == null) {
			return new ResponseEntity<>(new CustomError(
					"Unable to report network throughput. Network / Device doesn't exist or Device is not connected to this Network"),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new WifiNetworkModel(wifiNetworkEnt), HttpStatus.OK);

	}

}
