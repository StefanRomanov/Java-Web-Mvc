package com.excersise.virus.repos;

import com.excersise.virus.entities.Virus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VirusRepository extends JpaRepository<Virus, Long> {
}
