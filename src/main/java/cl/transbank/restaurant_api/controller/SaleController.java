package cl.transbank.restaurant_api.controller;

import cl.transbank.restaurant_api.entity.Sale;
import cl.transbank.restaurant_api.service.SaleService;
import cl.transbank.restaurant_api.service.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
public class SaleController {

    @Autowired
    SaleService saleService;

    @Autowired
    Sender sender;
    @Autowired
    JmsTemplate jmsTemplate;

    @GetMapping("/sales")
    ResponseEntity<List<Sale>> getAllSalesOfTheDay(@RequestBody(required = false) Sale sale) throws ParseException {

        if (sale == null) {
            sender.send("sales.q", String.valueOf(System.currentTimeMillis()));
        }else{
            sender.send("sales.q", String.valueOf(sale.getSaleDate().getTime()));
        }
        return ResponseEntity.ok((List<Sale>) jmsTemplate.receiveAndConvert("salesOfDay.q"));
    }

    public ResponseEntity<List<Sale>> getAllSalesOfTheDay(List<Sale> sales) throws ParseException {
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

        @PostMapping("/create")
    ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        return new ResponseEntity<>(saleService.createSale(sale), HttpStatus.CREATED);
    }
}
