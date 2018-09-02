package com.excersise.virus.services;

import com.excersise.virus.entities.Capital;
import com.excersise.virus.entities.models.CapitalNameAndIdViewModel;
import com.excersise.virus.repos.CapitalRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CapitalServiceImpl implements CapitalService {
    private CapitalRepository capitalRepository;
    private ModelMapper mapper;

    @Autowired
    public CapitalServiceImpl(CapitalRepository capitalRepository, ModelMapper mapper) {
        this.capitalRepository = capitalRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CapitalNameAndIdViewModel> simpleCapitals() {
        Type listType = new TypeToken<List<CapitalNameAndIdViewModel>>() {}.getType();
        List<Capital> capitals = this.capitalRepository.findAll();
        List<CapitalNameAndIdViewModel> models = new ArrayList<>();
        if(!capitals.isEmpty()){
            models.addAll((this.mapper.map(capitals,listType)));
        }


        return models;
    }
}
