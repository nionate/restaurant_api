package cl.transbank.restaurant_api.controller;

import cl.transbank.restaurant_api.entity.Sale;
import cl.transbank.restaurant_api.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

@RestController
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping("/sales")
    List<Sale> getAllSalesOfTheDay(@RequestBody(required = false) Sale sale) throws ParseException {
        if (sale == null) {
            return saleService.findSalesOfTheDay(new Date(System.currentTimeMillis()));
        }
        return saleService.findSalesOfTheDay(sale.getSaleDate());
    }

    @PostMapping("/create")
    ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        return new ResponseEntity<>(saleService.createSale(sale), HttpStatus.CREATED);
    }
}
