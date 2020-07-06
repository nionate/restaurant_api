package cl.transbank.restaurant_api.service;

import cl.transbank.restaurant_api.entity.Sale;
import cl.transbank.restaurant_api.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    private SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }
}
