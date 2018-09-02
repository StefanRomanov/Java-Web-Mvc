package com.example.cardealer.config;

import com.example.cardealer.entities.Part;
import com.example.cardealer.entities.Sale;
import com.example.cardealer.entities.Supplier;
import com.example.cardealer.entities.models.dto.PartAddDto;
import com.example.cardealer.entities.models.views.CarsAllExportModel;
import com.example.cardealer.entities.models.views.SaleExportModel;
import com.example.cardealer.entities.models.views.SupplierExportModel;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.math.MathContext;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper =  new ModelMapper();
        addCustomMappings(modelMapper);
        return modelMapper;
    }

    private void addCustomMappings(ModelMapper modelMapper){
        this.supplierExportMapping(modelMapper);
        this.saleExportMapping(modelMapper);
        this.partMapping(modelMapper);
    }

    private void supplierExportMapping(ModelMapper mapper){
        Converter<Supplier,SupplierExportModel> converter = new AbstractConverter<Supplier, SupplierExportModel>() {
            @Override
            protected SupplierExportModel convert(Supplier supplier) {
                SupplierExportModel model = new SupplierExportModel();
                model.setId(supplier.getId());
                model.setName(supplier.getName());
                model.setIsImporter(supplier.getIsImporter());
                model.setNumberOfParts(supplier.getParts().size());
                return model;
            }
        };

        mapper.addConverter(converter);
    }

    private void saleExportMapping(ModelMapper mapper){
        Converter<Sale, SaleExportModel> converter = new AbstractConverter<Sale, SaleExportModel>() {
            @Override
            protected SaleExportModel convert(Sale sale) {
                SaleExportModel model = new SaleExportModel();
                model.setCar(mapper.map(sale.getCar(),CarsAllExportModel.class));
                model.setCustomerName(sale.getCustomer().getName());
                BigDecimal discount = sale.getDiscount();
                if(sale.getCustomer().getIsYoungDriver()){
                    discount = discount.add(new BigDecimal(0.05));
                }
                model.setDiscount(discount.round(MathContext.DECIMAL64));
                model.setPrice(sale.getCar().getParts().stream().map(Part::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));

                return model;
            }
        };

        mapper.addConverter(converter);
    }

    private void partMapping(ModelMapper mapper){
        Converter<Part, PartAddDto> converter = new AbstractConverter<Part, PartAddDto>() {
            @Override
            protected PartAddDto convert(Part part) {
                PartAddDto model = new PartAddDto();
                model.setId(part.getId());
                model.setName(part.getName());
                model.setQuantity(part.getQuantity());
                model.setPrice(part.getPrice());
                model.setSupplier(part.getSupplier().getName());
                return model;
            }
        };
        mapper.addConverter(converter);
    }
}
