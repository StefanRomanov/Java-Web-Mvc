package com.excersise.virus.services;

import com.excersise.virus.entities.Virus;
import com.excersise.virus.entities.models.AddVirusModel;
import com.excersise.virus.entities.models.VirusAllModel;
import com.excersise.virus.repos.CapitalRepository;
import com.excersise.virus.repos.VirusRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Transactional
@Service
public class VirusServiceImpl implements VirusService {

    private VirusRepository virusRepository;
    private CapitalRepository capitalRepository;
    private ModelMapper modelMapper;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository, CapitalRepository capitalRepository, ModelMapper modelMapper) {
        this.virusRepository = virusRepository;
        this.capitalRepository = capitalRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<VirusAllModel> findAll() {
        List<Virus> viruses = this.virusRepository.findAll();

        Type listType = new TypeToken<List<VirusAllModel>>() {}.getType();
        return this.modelMapper.map(viruses,listType);
    }

    @Override
    public Boolean addVirus(AddVirusModel virusModel) {

        Virus virus = this.modelMapper.map(virusModel, Virus.class);

        for (Long capId: virusModel.getCapIds()) {
            virus.getCapitals().add(this.capitalRepository.getOne(capId));
        }

        Virus result =  this.virusRepository.save(virus);
        return result != null;
    }

    @Override
    public Boolean updateVirus(AddVirusModel addVirusModel) {
        Virus virus = this.virusRepository.getOne(addVirusModel.getId());

        if(virus == null){
            return false;
        }

        Virus newVirus = this.modelMapper.map(addVirusModel,Virus.class);
        newVirus.setReleasedOn(virus.getReleasedOn());
        for (Long capId: addVirusModel.getCapIds()) {
            newVirus.getCapitals().add(this.capitalRepository.getOne(capId));
        }

        Virus result = this.virusRepository.saveAndFlush(newVirus);

        return result != null;
    }

    @Override
    public AddVirusModel getById(Long id) {
        return this.modelMapper.map(this.virusRepository.getOne(id),AddVirusModel.class);
    }

    @Override
    public void deleteById(Long id) {
        this.virusRepository.deleteById(id);
    }
}
