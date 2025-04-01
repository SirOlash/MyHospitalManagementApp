package org.HospitalProjectCholda.data.repositories;

import org.HospitalProjectCholda.data.models.Patient;
import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
//    boolean existsByEmail(String email);
//    Patient findByUsername(String username);
//    Patient findByEmail(String email);
//    boolean existsByUsername(String username);

}

