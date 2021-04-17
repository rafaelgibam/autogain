package br.com.autogain.consumer.iqoption.service;

import br.com.autogain.consumer.iqoption.rest.request.ChangeBalanceRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChangeBalanceBaseService extends BaseService {


	/**
	 * Change active balance by its id
	 * @param id
	 * @param headers
	 * @return ResponseEntity
	 */
	public ResponseEntity<String> changeBalance(Long id, HttpHeaders headers) {
		HttpEntity<String> entity = new HttpEntity(new ChangeBalanceRequest(id), headers);
		return restTemplate.postForEntity("https://iqoption.com/api/profile/changebalance",
				entity , String.class);
	}
	
}
