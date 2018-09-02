package com.example.cardealer.services;

import com.example.cardealer.entities.Sale;
import com.example.cardealer.entities.models.dto.SaleAddDto;
import com.example.cardealer.entities.models.views.*;
import com.example.cardealer.repos.CarRepository;
import com.example.cardealer.repos.CustomerRepository;
import com.example.cardealer.repos.SaleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class SaleServiceImpl implements SaleService {

    private CarService carService;
    private CustomerService customerService;
    private ModelMapper modelMapper;
    private SaleRepository saleRepository;
    private CarRepository carRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public SaleServiceImpl(CarService carService, CustomerService customerService, ModelMapper modelMapper, SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.carService = carService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<SaleExportModel> allSales() {
        List<Sale> sales = this.saleRepository.findAll();

        Type type = new TypeToken<List<SaleExportModel>>(){}.getType();
        return this.modelMapper.map(sales,type);
    }

    @Override
    public SaleExportModel saleById(Long id) {
        Sale sale = this.saleRepository.getOne(id);
        if(sale == null){
            return null;
        }

        return this.modelMapper.map(sale,SaleExportModel.class);
    }

    @Override
    public List<SaleExportModel> discountedSales() {
        List<Sale> sales = this.saleRepository.findAll();

        Type type = new TypeToken<List<SaleExportModel>>(){}.getType();
        List<SaleExportModel> models = this.modelMapper.map(sales,type);
        return models.stream().filter(e -> e.getDiscount().compareTo(BigDecimal.ZERO) > 0).collect(Collectors.toList());
    }

    @Override
    public List<SaleExportModel> discountedByConcretePercent(String percent) {
        List<Sale> sales = this.saleRepository.findAll();

        BigDecimal actualPercent = new BigDecimal(percent).divide(new BigDecimal(100));
        Type type = new TypeToken<List<SaleExportModel>>(){}.getType();
        List<SaleExportModel> models = this.modelMapper.map(sales,type);
        return models.stream().filter(e -> e.getDiscount().compareTo(actualPercent) == 0).collect(Collectors.toList());
    }

    @Override
    public SaleReviewView saleForReview(SaleAddDto model) {
        SaleReviewView exportModel = new SaleReviewView();

        CarsAllExportModel car = this.carService.carWithParts(model.getCar());
        CustomerAscDescModel customerModel =  this.customerService.getOneForSale(model.getCustomer());

        exportModel.setCar(car);
        exportModel.setCustomer(customerModel);


        exportModel.setDiscount(model.getDiscount().divide(new BigDecimal(100)));
        exportModel.setPrice(car.getParts().stream().map(PartExportModel::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));

        return exportModel;
    }

    @Override
    public Boolean addSale(SaleAddDto model) {
        Sale sale = new Sale();
        sale.setCar(this.carRepository.getOne(model.getCar()));
        sale.setCustomer(this.customerRepository.getOne(model.getCustomer()));
        sale.setDiscount(model.getDiscount());

        Sale result = this.saleRepository.saveAndFlush(sale);
        return result != null;
    }


}
