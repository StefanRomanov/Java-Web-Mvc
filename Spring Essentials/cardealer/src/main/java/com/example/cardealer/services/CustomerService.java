package com.example.cardealer.services;

import com.example.cardealer.entities.models.dto.CustomerAddDto;
import com.example.cardealer.entities.models.views.CustomerAscDescModel;
import com.example.cardealer.entities.models.views.CustomerInfoModel;

import java.util.List;

public interface CustomerService {
    List<CustomerAscDescModel> getAllAscending();

    List<CustomerAscDescModel> getAllDescending();

    CustomerInfoModel customerById(Long id);

    Long customerAdd(CustomerAddDto model);

    CustomerAddDto getCustomerForEdit(Long id);

    CustomerAscDescModel getOneForSale(Long id);
}
