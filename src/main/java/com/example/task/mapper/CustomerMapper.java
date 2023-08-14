package com.example.task.mapper;

import com.example.task.dto.CustomerDto;
import com.example.task.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "fname", target = "fname")
    @Mapping(source = "lname", target = "lname")
    @Mapping(source = "username", target = "username")
    CustomerDto customerToCustomerDto(Customer customer);
}
