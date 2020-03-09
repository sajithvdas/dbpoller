package com.ibsplc.poller.dbpoller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcMessageHandler {

	@Autowired
	JmsTemplate jmsTemplate;
	
	public void handleJdbcMessage(List<Map<String, Object>> message) {
		for (Map<String, Object> resultMap: message) {
			jmsTemplate.convertAndSend("notification.queue", resultMap.get("EDIFACT_JSON"));
			System.out.println("Message-->"+resultMap.get("EDIFACT_JSON")+" Sent to Queue-->"+" notification.queue");
		}
	}
}
