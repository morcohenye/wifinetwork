package com.checkpoint.wifiproject.repositories;

import org.springframework.data.repository.CrudRepository;

import com.checkpoint.wifiproject.modules.WifiNetworkEntity;

public interface WifiNetworkRepository extends CrudRepository<WifiNetworkEntity, String> {

}
