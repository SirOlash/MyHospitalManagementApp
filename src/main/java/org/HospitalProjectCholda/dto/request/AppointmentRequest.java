package org.HospitalProjectCholda.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentRequest {
    @NotNull(message = "Appointment time must be provided")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @FutureOrPresent(message = "Date cannot be in the past")
    private LocalDateTime appointmentTime;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String doctorEmail;

//    private Patient patient;
}
