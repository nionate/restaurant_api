package cl.transbank.restaurant_api.controller;

import cl.transbank.restaurant_api.entity.Sale;
import cl.transbank.restaurant_api.service.SaleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class SaleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private SaleService saleService;

    @Test
    void shouldReturnAListWithAllSales() throws Exception {
        List<Sale> sales = new ArrayList<Sale>();
        sales.add(new Sale( 1L, 12345L, "04-07-2020", "11.111.111-1", "22.222.222-2", 10000L));
        sales.add(new Sale( 2L, 12346L, "04-07-2020", "12.112.111-2", "24.224.222-4", 2000L));
        when(saleService.findAll()).thenReturn(sales);

        mockMvc.perform(MockMvcRequestBuilders.get("/sales")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }
}
