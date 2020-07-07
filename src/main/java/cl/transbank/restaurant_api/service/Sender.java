package cl.transbank.restaurant_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String destinationQueue, String date) {
        jmsTemplate.convertAndSend(destinationQueue, date);
    }
}
