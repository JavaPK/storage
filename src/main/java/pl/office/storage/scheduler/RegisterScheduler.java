package pl.office.storage.scheduler;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.office.storage.api.RegisterInstanceDTO;
import pl.office.storage.config.properties.StorageManagerProperties;
import pl.office.storage.config.properties.StorageProperties;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisterScheduler {

	private final RestTemplate restTemplate;
	private final StorageManagerProperties storageManagerProperties;
	private final StorageProperties storageProperties;

	@Scheduled(fixedRateString = "${storage-manager.register-rate}")
	public void registerInManager(){

		RegisterInstanceDTO request = RegisterInstanceDTO.builder()
				.id(storageProperties.getId())
				.instatnceType(storageProperties.getInstanceType())
				.host(storageProperties.getServerHost())
				.port(storageProperties.getServerPort())
				.build();

		ResponseEntity<Boolean> response = restTemplate.postForEntity(getRegisterUrl(), request, Boolean.class);

		if(response.getStatusCode().is2xxSuccessful() && response.getBody()){
			log.debug("Connected to storage manager at: {}", storageManagerProperties.getUrl());
		}
	}

	private String getRegisterUrl(){
		return storageManagerProperties.getUrl() + storageManagerProperties.getRegister();
	}
}
