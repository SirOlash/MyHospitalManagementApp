package org.HospitalProjectCholda.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
@Document(collection = "appointment")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Appointment {
    @Id
    private String id;
//    private static final DateTimeFormatter APPOINTMENT_FORMATTER =
//            new DateTimeFormatterBuilder()
//                    .parseCaseInsensitive()
//                    .appendPattern("dd-MM-yyyy h:mm a")
//                    .toFormatter(Locale.ENGLISH);

    private LocalDateTime appointmentTime;
    private Patient patient;
    private Doctor doctor;
    private String description;
    private String status = "PENDING";


//    public Appointment(String appointmentDateTime, Patient patient, String doctorId, String description) {
//        this.appointmentTime = LocalDateTime.parse(appointmentDateTime, APPOINTMENT_FORMATTER);
//        this.patient = patient;
//        this.doctorId = doctorId;
//        this.description = description;
//    }
}
