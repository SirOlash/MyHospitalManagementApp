package org.HospitalProjectCholda.data.repositories;


import org.HospitalProjectCholda.data.models.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, String> {

}
