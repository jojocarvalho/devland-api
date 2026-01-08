package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.enums.FileType;
import br.com.devland.devland_api.model.MaterialModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record MaterialRequest(

        @NotBlank(message = "O título é obrigatório")
        String title,

        @NotBlank(message = "O tipo do arquivo é obrigatório")
        @JsonProperty("file_type")
        FileType fileType,

        @NotBlank(message = "A URL do arquivo é obrigatória")
        @JsonProperty("file_url")
        String fileUrl,

        @NotBlank(message = "A visibilidade é obrigatória")
        @JsonProperty("is_public")
        boolean isPublic,

        @JsonProperty("course_module")
        String courseModuleId

) {

    public MaterialModel toEntity() {
        MaterialModel materialModel = new MaterialModel();
        materialModel.setMaterialId("MATERIAL_" + java.util.UUID.randomUUID().toString().toUpperCase());
        materialModel.setTitle(this.title);
        materialModel.setFileType(this.fileType);
        materialModel.setFileUrl(this.fileUrl);
        materialModel.setPublic(this.isPublic);
        return materialModel;
    }
}

