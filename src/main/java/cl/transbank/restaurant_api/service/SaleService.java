package cl.transbank.restaurant_api.service;

import cl.transbank.restaurant_api.entity.Sale;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {
    public List<Sale> findAll() {
        return new ArrayList<>();
    }

    public Sale createSale(Sale sale) {
        return null;
    }
}
