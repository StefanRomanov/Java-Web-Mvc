package com.example.cardealer.services;

import com.example.cardealer.entities.Part;
import com.example.cardealer.entities.Supplier;
import com.example.cardealer.entities.models.dto.PartAddDto;
import com.example.cardealer.repos.PartRepository;
import com.example.cardealer.repos.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Transactional
@Service
public class PartServiceImpl implements PartService {

    private PartRepository partRepository;
    private ModelMapper modelMapper;
    private SupplierRepository supplierRepository;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper, SupplierRepository supplierRepository) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.supplierRepository = supplierRepository;
    }



    @Override
    public void addPart(PartAddDto model) {
        Part part = new Part();
        part.setId(model.getId());
        part.setName(model.getName());
        part.setPrice(model.getPrice());
        part.setQuantity(model.getQuantity());
        Supplier supplier = this.supplierRepository.getFirstByName(model.getSupplier());
        part.setSupplier(supplier);

        this.partRepository.saveAndFlush(part);
    }

    @Override
    public List<PartAddDto> getAllParts() {
        List<Part> parts = this.partRepository.findAll();
        Type type = new TypeToken<List<PartAddDto>>(){}.getType();
        return this.modelMapper.map(parts,type);
    }

    @Override
    public PartAddDto getOneById(Long id) {
        Part part = this.partRepository.getOne(id);
        if(part == null){
            return null;
        }

        return this.modelMapper.map(part, PartAddDto.class);
    }

    @Override
    public Boolean deletePart(Long id) {
        Part part = this.partRepository.getOne(id);

        if(part == null){
            return false;
        }

        this.partRepository.delete(part);

        return true;
    }
}
