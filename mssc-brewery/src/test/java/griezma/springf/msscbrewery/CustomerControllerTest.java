package griezma.springf.msscbrewery;

import com.fasterxml.jackson.databind.ObjectMapper;
import griezma.springf.msscbrewery.services.CustomerService;
import griezma.springf.msscbrewery.web.controller.CustomerController;
import griezma.springf.msscbrewery.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    CustomerService service;
    @Autowired
    ObjectMapper json;

    CustomerDto validCustomer() {
        UUID customerId = UUID.randomUUID();
        return new CustomerDto(customerId, "mani");
    }

    CustomerDto invalidCustomer() {
        UUID customerId = UUID.randomUUID();
        return new CustomerDto(customerId, "ma");
    }

    @Test
    public void shouldFindCustomerById() throws Exception {
        CustomerDto customer = validCustomer();
        when(service.getCustomerById(any(UUID.class)))
                .thenReturn(customer);

        mvc.perform(get("/api/v1/customer/" + customer.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("mani")));
    }

    @Test
    public void shouldAcceptNewCustomer() throws Exception {
        String customerJson = json.writeValueAsString(validCustomer());
        mvc.perform(post("/api/v1/customer")
                .content(customerJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().exists("Location"))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void shouldRejectInvalidCustomer() throws Exception {
        String customerJson = json.writeValueAsString(invalidCustomer());
        mvc.perform(post("/api/v1/customer")
                .content(customerJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
