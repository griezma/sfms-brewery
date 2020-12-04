package griezma.springf.msscbrewery.data;

import griezma.springf.msscbrewery.data.utils.DateTimeMapper;
import griezma.springf.msscbrewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = DateTimeMapper.class)
public interface CustomerMapper {
    CustomerDto asCustomerDto(Customer entity);

    Customer toCustomer(CustomerDto dto);
}
