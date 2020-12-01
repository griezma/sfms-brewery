package griezma.springf.msscbrewery;

import griezma.springf.msscbrewery.services.CustomerService;
import griezma.springf.msscbrewery.web.controller.CustomerController;
import griezma.springf.msscbrewery.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    CustomerService service;

    CustomerDto randomCustomer() {
        UUID customerId = UUID.randomUUID();
        return new CustomerDto(customerId, "mani");
    }

    @Test
    public void shouldFindCustomerById() throws Exception {
        CustomerDto customer = randomCustomer();
        when(service.getCustomerById(any(UUID.class)))
                .thenReturn(customer);

        mvc.perform(get("/api/v1/customer/" + customer.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("mani")));
    }
}
