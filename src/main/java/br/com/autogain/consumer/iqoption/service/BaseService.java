package br.com.autogain.consumer.iqoption.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public abstract class BaseService<T> {

	protected RestTemplate restTemplate;

	public BaseService() {
		this.restTemplate = new RestTemplate();
	}

}
