package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.model.CourseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record CourseRequest(

        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "A descrição é obrigatória")
        String description,

        @NotBlank(message = "O número de aulas é obrigatório")
        @JsonProperty("number_of_classes")
        int numberOfClasses,

        @NotBlank(message = "A duração em horas é obrigatória")
        int durationHours
) {

    public CourseModel toEntity() {
        CourseModel courseModel = new CourseModel();
        courseModel.setCourseId("COURSE_" + java.util.UUID.randomUUID().toString().toUpperCase());
        courseModel.setName(this.name);
        courseModel.setDescription(this.description);
        courseModel.setNumberOfClasses(this.numberOfClasses);
        courseModel.setDurationHours(this.durationHours);
        return courseModel;
    }
}
