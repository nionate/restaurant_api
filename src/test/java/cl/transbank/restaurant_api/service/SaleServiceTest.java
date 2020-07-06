package cl.transbank.restaurant_api.service;

import cl.transbank.restaurant_api.entity.Sale;
import cl.transbank.restaurant_api.repository.SaleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SaleServiceTest {

    @Autowired
    private SaleRepository saleRepository;

    @Test
    void shouldGetAllSales() {
        Sale sale = new Sale(12345L, LocalDate.now().toString(), "11.111.111-1", "22.222.222-2", 1000);
        saleRepository.save(sale);
        SaleService saleService = new SaleService(saleRepository);

        List<Sale> saleList = saleService.findAll();
        Sale lastSale = saleList.get(saleList.size()-1);

        assertEquals(sale.getInvoice(), lastSale.getInvoice());
        assertEquals(sale.getCiSeller(), lastSale.getCiSeller());
    }
}
