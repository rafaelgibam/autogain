package br.com.autogain.consumer.iqoption.service;

import br.com.autogain.consumer.iqoption.rest.request.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class LoginBaseService extends BaseService {
	
	public ResponseEntity<String> login(String email, String password) {
		return restTemplate.postForEntity("https://auth.iqoption.com/api/v2/login",
				new LoginRequest(email, password), String.class);
	}

}
