package cl.transbank.restaurant_api.service;

import cl.transbank.restaurant_api.entity.Sale;
import cl.transbank.restaurant_api.repository.SaleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SaleServiceTest {

    @Autowired
    private SaleRepository saleRepository;

    @Test
    void shouldGetAllSales() {
        Sale sale = new Sale(12345L, new Date(System.currentTimeMillis()), "11.111.111-1", "22.222.222-2", 1000);
        saleRepository.save(sale);
        SaleService saleService = new SaleService(saleRepository);

        List<Sale> saleList = saleService.findAll();
        Sale lastSale = saleList.get(saleList.size()-1);

        assertEquals(sale.getInvoice(), lastSale.getInvoice());
        assertEquals(sale.getCiSeller(), lastSale.getCiSeller());
    }

    @Test
    void shouldCreateANewSale() {
        Sale sale = new Sale(12345L, new Date(System.currentTimeMillis()), "11.111.111-1", "22.222.222-2", 1000);
        SaleService saleService = new SaleService(saleRepository);

        saleService.createSale(sale);
        List<Sale> saleList = saleService.findAll();

        Sale expectedSale = saleList.get(saleList.size()-1);

        assertEquals(sale.getInvoice(), expectedSale.getInvoice());
        assertEquals(sale.getCiSeller(), expectedSale.getCiSeller());
    }

    @Test
    void shouldGetAllSalesFromToday() throws ParseException {
        Sale sale = new Sale(12345L, new Date(System.currentTimeMillis()), "11.111.111-1", "22.222.222-2", 1000);
        Date someDate = new Date(LocalDate.of(2020, 7, 5).toEpochDay());
        Sale sale2 = new Sale(12346L, someDate, "11.111.111-1", "22.222.222-2", 1000);

        SaleService saleService = new SaleService(saleRepository);
        saleService.createSale(sale);
        saleService.createSale(sale2);

        List<Sale> expectedSales = saleService.findSalesOfTheDay(someDate);

        assertEquals(1, expectedSales.size());
        assertEquals(12346, expectedSales.get(0).getInvoice());
    }

    private Date convertToDate(LocalDate localDate) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = (Date) Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        return date;
    }
}
