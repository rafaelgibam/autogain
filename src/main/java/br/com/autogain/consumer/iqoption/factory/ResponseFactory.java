package br.com.autogain.consumer.iqoption.factory;

import br.com.autogain.consumer.iqoption.ws.response.CandleRootMessage;
import br.com.autogain.consumer.iqoption.ws.response.ProfileRootMessage;
import br.com.autogain.consumer.iqoption.event.Events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseFactory {
	
	public static Object transform(Events ev, String message) {
		if(Events.CANDLE.equals(ev)) {
			return convert(message, CandleRootMessage.class);
		} else if (Events.PROFILE.equals(ev)) {
			return convert(message, ProfileRootMessage.class);
		}
		return null;
	}
	
	private static Object convert(String message, Class<?> c) {
		try {
			return new ObjectMapper().readValue(message, c);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
