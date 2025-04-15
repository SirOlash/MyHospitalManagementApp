package org.HospitalProjectCholda.data.repositories;

import org.HospitalProjectCholda.data.models.MedicalHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicalHistoryRepository extends MongoRepository<MedicalHistory, String> {

}
