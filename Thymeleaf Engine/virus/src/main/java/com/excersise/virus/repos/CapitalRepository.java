package com.excersise.virus.repos;

import com.excersise.virus.entities.Capital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapitalRepository extends JpaRepository<Capital,Long> {
}
