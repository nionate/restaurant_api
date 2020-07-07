package cl.transbank.restaurant_api.controller;

import cl.transbank.restaurant_api.entity.Sale;
import cl.transbank.restaurant_api.entity.User;
import cl.transbank.restaurant_api.service.Receiver;
import cl.transbank.restaurant_api.service.SaleService;
import cl.transbank.restaurant_api.service.Sender;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SaleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Sender sender;
    @Autowired
    private Receiver receiver;
    @MockBean
    private SaleService saleService;
    @MockBean
    private JmsTemplate jmsTemplate;

    @Test
    public void shouldReturnAListWithAllSales() throws Exception {
        String token = obtainAccessToken("tbk", "superpassw0rd!");

        List<Sale> sales = new ArrayList<Sale>();
        Date salesDay = new Date(System.currentTimeMillis());
        sales.add(new Sale(12345L, salesDay, "11.111.111-1", "22.222.222-2", 10000L));
        sales.add(new Sale(12346L, salesDay, "12.112.111-2", "24.224.222-4", 2000L));
        when(saleService.findSalesOfTheDay(any())).thenReturn(sales);
        when(jmsTemplate.receiveAndConvert("salesOfDay.q")).thenReturn(sales);

       mockMvc.perform(MockMvcRequestBuilders.get("/sales")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(print());
    }

    @Test
    public void shouldAddANewSale() throws Exception {
        String token = obtainAccessToken("tbk", "superpassw0rd!");

        Sale sale = new Sale(12345L, new Date(System.currentTimeMillis()), "11.111.111-1", "22.222.222-2", 10000L);
        when(saleService.createSale(any(Sale.class))).thenReturn(sale);

        mockMvc.perform(post("/create")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(sale))
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.invoice").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String obtainAccessToken(String username, String password) throws Exception {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        ResultActions result
                = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("token").toString();
    }
}
