package cl.transbank.restaurant_api.controller;

import cl.transbank.restaurant_api.entity.Sale;
import cl.transbank.restaurant_api.service.SaleService;
import org.omg.CORBA.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping("/sales")
    List<Sale> getAllSales() {
        return saleService.findAll();
    }

    @PostMapping("/create")
    ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        return new ResponseEntity<Sale>(saleService.createSale(sale), HttpStatus.OK);
    }
}
