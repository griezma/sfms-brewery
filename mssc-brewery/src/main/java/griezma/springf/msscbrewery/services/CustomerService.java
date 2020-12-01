package griezma.springf.msscbrewery.services;

import griezma.springf.msscbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder()
                .id(customerId)
                .name("mani")
                .build();
    }
}
