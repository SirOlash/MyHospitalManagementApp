package org.HospitalProjectCholda.data.repositories;

import org.HospitalProjectCholda.data.models.Appointment;
import org.HospitalProjectCholda.data.models.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    Optional<Appointment> findByDoctor_EmailAndStatus(String doctorEmail, String status);

}

