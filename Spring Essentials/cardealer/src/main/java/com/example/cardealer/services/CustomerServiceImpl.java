package com.example.cardealer.services;

import com.example.cardealer.entities.Customer;
import com.example.cardealer.entities.Part;
import com.example.cardealer.entities.Sale;
import com.example.cardealer.entities.models.dto.CustomerAddDto;
import com.example.cardealer.entities.models.views.CustomerAscDescModel;
import com.example.cardealer.entities.models.views.CustomerInfoModel;
import com.example.cardealer.repos.CustomerRepository;
import com.example.cardealer.repos.SaleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private SaleRepository saleRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, SaleRepository saleRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CustomerAscDescModel> getAllAscending() {
        List<Customer> customers = this.customerRepository.findAll().stream().sorted((a,b) -> {
            if(a.getBirthDate().compareTo(b.getBirthDate()) != 0){
                return a.getBirthDate().compareTo(b.getBirthDate());
            } else {
                return b.getIsYoungDriver().compareTo(a.getIsYoungDriver());
            }
        }).collect(Collectors.toList());

        Type type = new TypeToken<List<CustomerAscDescModel>>() {}.getType();

        return this.modelMapper.map(customers,type);
    }

    @Override
    public List<CustomerAscDescModel> getAllDescending() {
        List<Customer> customers = this.customerRepository.findAll().stream().sorted((a,b) -> {
                    if(b.getBirthDate().compareTo(a.getBirthDate()) != 0){
                        return b.getBirthDate().compareTo(a.getBirthDate());
                    } else {
                        return b.getIsYoungDriver().compareTo(a.getIsYoungDriver());
                    }
                }).collect(Collectors.toList());;
        Type type = new TypeToken<List<CustomerAscDescModel>>() {}.getType();

        return this.modelMapper.map(customers,type);
    }

    @Override
    public CustomerInfoModel customerById(Long id) {
        Customer customer = this.customerRepository.getOne(id);
        if(customer == null){
            return null;
        }

        List<Sale> sales = this.saleRepository.getAllByCustomer(customer);
        CustomerInfoModel model = new CustomerInfoModel();
        model.setName(customer.getName());
        model.setCarsCount(sales.size());

        BigDecimal spent = new BigDecimal(BigInteger.ZERO);

        for (Sale sale : sales) {
            BigDecimal discount = sale.getDiscount();
            if(customer.getIsYoungDriver()){
                discount = sale.getDiscount().add(new BigDecimal(0.05));
            }
            BigDecimal total = spent.add(sale.getCar().getParts().stream().map(Part::getPrice)
                    .reduce(BigDecimal::add).orElse(BigDecimal.ZERO));

            spent = total.subtract(total.multiply(discount));
        }
        model.setTotalSpent(spent);

        return model;
    }

    @Override
    public Long customerAdd(CustomerAddDto model) {
        Customer customer = new Customer();
        customer.setId(model.getId());
        customer.setName(model.getName());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        customer.setBirthDate(LocalDate.parse(model.getBirthDate(),formatter));

        Integer period = Period.between(customer.getBirthDate(),LocalDate.now()).getYears();
        customer.setIsYoungDriver(period < 20);
        Customer result = this.customerRepository.saveAndFlush(customer);
        if(result != null){
            return result.getId();
        }

        return null;
    }

    @Override
    public CustomerAddDto getCustomerForEdit(Long id) {
        Customer customer = this.customerRepository.getOne(id);
        if(customer == null){
            return null;
        }

        CustomerAddDto model = new CustomerAddDto();
        model.setId(customer.getId());
        model.setName(customer.getName());
        model.setBirthDate(customer.getBirthDate().toString());

        return model;
    }

    @Override
    public CustomerAscDescModel getOneForSale(Long id) {
        Customer customer = this.customerRepository.getOne(id);
        return this.modelMapper.map(customer,CustomerAscDescModel.class);
    }
}
