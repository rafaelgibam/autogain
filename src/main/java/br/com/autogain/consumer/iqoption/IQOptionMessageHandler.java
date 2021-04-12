package br.com.autogain.consumer.iqoption;

import br.com.autogain.consumer.iqoption.IQOptionWS.MessageHandler;
import br.com.autogain.consumer.iqoption.event.EventManager;
import br.com.autogain.converter.MessageConverter;
import br.com.autogain.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IQOptionMessageHandler implements MessageHandler {
	
	private final Logger logger = LoggerFactory.getLogger(IQOptionMessageHandler.class);
	
	private EventManager eventManager;

	private MessageConverter messageConverter;

	public IQOptionMessageHandler(EventManager eventManager) {
		this.messageConverter = new MessageConverter();
		this.eventManager = eventManager;
	}

	@Override
	public void handleMessage(String message) {
		this.eventManager.notif(message);
	}
}
