package cl.transbank.restaurant_api.service;

import cl.transbank.restaurant_api.controller.SaleController;
import cl.transbank.restaurant_api.entity.Sale;
import cl.transbank.restaurant_api.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.TimeZone;

@Service
public class SaleService {

    private SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public List<Sale> findSalesOfTheDay(Date now) throws ParseException {
        return saleRepository.findSalesOfTheDay(now);
    }
}
