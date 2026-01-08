package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.model.CourseModuleModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record CourseModuleRequest (

        @NotBlank(message = "O título é obrigatório")
        String title,

        @NotBlank(message = "O índice de ordem é obrigatório")
        @JsonProperty("order_index")
        int orderIndex,

        @JsonProperty("course_id")
        String courseId

) {

    public CourseModuleModel toEntity() {
        CourseModuleModel courseModuleModel = new CourseModuleModel();
        courseModuleModel.setModuleId("MODULE_" + java.util.UUID.randomUUID().toString().toUpperCase());
        courseModuleModel.setTitle(this.title);
        courseModuleModel.setOrderIndex(this.orderIndex);
        return courseModuleModel;
    }
}
