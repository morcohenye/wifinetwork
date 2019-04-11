package com.checkpoint.wifiproject.repositories;

import org.springframework.data.repository.CrudRepository;

import com.checkpoint.wifiproject.modules.DeviceEntity;

public interface DeviceRepository extends CrudRepository<DeviceEntity, String> {

}
