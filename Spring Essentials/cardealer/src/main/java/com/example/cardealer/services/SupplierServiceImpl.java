package com.example.cardealer.services;

import com.example.cardealer.entities.Part;
import com.example.cardealer.entities.Supplier;
import com.example.cardealer.entities.models.dto.SupplierAddModel;
import com.example.cardealer.entities.models.views.SupplierExportModel;
import com.example.cardealer.repos.PartRepository;
import com.example.cardealer.repos.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;
    private PartRepository partRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, PartRepository partRepository) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.partRepository = partRepository;
    }

    @Override
    public List<SupplierExportModel> suppliersByIsImporter(Boolean isImporter) {
        List<Supplier> suppliers = this.supplierRepository.getAllByIsImporter(isImporter);
        Type type = new TypeToken<List<SupplierExportModel>>(){}.getType();

        return this.modelMapper.map(suppliers,type);
    }

    @Override
    public List<String> getAllSupplierNames() {
        List<String> result = this.supplierRepository.findAll().stream().map(Supplier::getName).collect(Collectors.toList());;
        return result;
    }

    @Override
    public SupplierAddModel getOne(Long id) {
        Supplier supplier = this.supplierRepository.getOne(id);
        if(supplier == null){
            return null;
        }
        return this.modelMapper.map(supplier,SupplierAddModel.class);
    }

    @Override
    public Boolean addSupplier(SupplierAddModel model) {

        Supplier supplier = this.modelMapper.map(model,Supplier.class);

        if(model.getId() != null){
            supplier.setId(model.getId());
        }

        Supplier result = this.supplierRepository.saveAndFlush(supplier);
        return result != null;
    }

    @Override
    public List<SupplierExportModel> allSuppliers() {
        List<Supplier> suppliers = this.supplierRepository.findAll();
        Type type = new TypeToken<List<SupplierExportModel>>(){}.getType();

        return this.modelMapper.map(suppliers,type);
    }

    @Override
    public void delete(Long id) {
        Supplier supplier = this.supplierRepository.getOne(id);

        if(supplier != null){

            for (Part part : supplier.getParts()) {
                this.partRepository.delete(part);
            }

            this.supplierRepository.delete(supplier);
        }
    }
}
