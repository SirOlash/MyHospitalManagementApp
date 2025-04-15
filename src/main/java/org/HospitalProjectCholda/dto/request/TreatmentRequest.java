package org.HospitalProjectCholda.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TreatmentRequest {
    @NotBlank(message = "Treatment description is required")
    private String treatment;
}
