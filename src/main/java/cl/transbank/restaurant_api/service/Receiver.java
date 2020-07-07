package cl.transbank.restaurant_api.service;

import cl.transbank.restaurant_api.entity.Sale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

@Service
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private SaleService saleService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "sales.q")
    public void receive(String dateInMilliSeconds) throws ParseException {
        Date date = new Date((Long.parseLong(dateInMilliSeconds)));
        LOGGER.info("Message received '{}'", date);
        List<Sale> salesOfTheDay = saleService.findSalesOfTheDay(date);

        jmsTemplate.convertAndSend("salesOfDay.q", salesOfTheDay);
    }
}
