package cl.transbank.restaurant_api.controller;

import cl.transbank.restaurant_api.entity.Sale;
import cl.transbank.restaurant_api.service.SaleService;
import cl.transbank.restaurant_api.service.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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
    ResponseEntity<List<Sale>> getAllSalesOfTheDay(@RequestParam(required = false) String date) throws ParseException {

        if (date == null) {
            sender.send("sales.q", String.valueOf(System.currentTimeMillis()));
        }else{
            Date date1 = Date.valueOf(date);
            sender.send("sales.q", String.valueOf(date1.getTime()));
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
