package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Absence;
import java.util.List;

@Repository
public interface AbsenceRepository extends MongoRepository<Absence, String> {
    List<Absence> findByTitreContaining(String titre);
}
