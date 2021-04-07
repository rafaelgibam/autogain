package br.com.autogain.consumer.iqoption.service;

import org.springframework.web.client.RestTemplate;

public abstract class BaseService {
	
	protected RestTemplate restTemplate;
	
	public BaseService() {
		this.restTemplate = new RestTemplate();
	}

}
