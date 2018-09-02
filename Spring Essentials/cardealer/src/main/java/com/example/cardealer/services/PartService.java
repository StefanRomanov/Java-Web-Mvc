package com.example.cardealer.services;

import com.example.cardealer.entities.models.dto.PartAddDto;

import java.util.List;

public interface PartService {
    void addPart(PartAddDto model);
    List<PartAddDto> getAllParts();
    PartAddDto getOneById(Long id);
    Boolean deletePart(Long id);
}
