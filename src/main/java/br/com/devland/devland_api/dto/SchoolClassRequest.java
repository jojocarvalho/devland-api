package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.enums.SchoolClassStatus;
import br.com.devland.devland_api.model.SchoolClassModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record SchoolClassRequest(

        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "A data de início é obrigatória")
        @JsonProperty("start_date")
        String startDate,

        @NotBlank(message = "A data de término é obrigatória")
        @JsonProperty("end_date")
        String endDate,

        @NotBlank(message = "O status é obrigatório")
        SchoolClassStatus status,

        @JsonProperty("course_id")
        String course,

        @JsonProperty("teacher_id")
        String teacher

) {

    public SchoolClassModel toEntity() {
        SchoolClassModel schoolClassModel = new SchoolClassModel();
        schoolClassModel.setSchoolClassId("CLASS_" + java.util.UUID.randomUUID().toString().toUpperCase());
        schoolClassModel.setName(this.name);
        schoolClassModel.setStartDate(this.startDate);
        schoolClassModel.setEndDate(this.endDate);
        schoolClassModel.setStatus(this.status);
        return schoolClassModel;
    }

}
