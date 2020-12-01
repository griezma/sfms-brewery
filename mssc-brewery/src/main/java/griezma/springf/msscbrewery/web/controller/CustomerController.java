package griezma.springf.msscbrewery.web.controller;

import griezma.springf.msscbrewery.services.CustomerService;
import griezma.springf.msscbrewery.web.model.CustomerDto;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID customerId) {
        CustomerDto customer = service.getCustomerById(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
