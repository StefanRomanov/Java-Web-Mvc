package com.excersise.virus.services;

import com.excersise.virus.entities.Virus;
import com.excersise.virus.entities.models.AddVirusModel;
import com.excersise.virus.entities.models.VirusAllModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

public interface VirusService {
    public List<VirusAllModel> findAll();

    Boolean addVirus(AddVirusModel addVirusModel);

    Boolean updateVirus(AddVirusModel addVirusModel);

    AddVirusModel getById(Long id);

    void deleteById(Long id);
}
