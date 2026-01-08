package br.com.devland.devland_api.model;

import br.com.devland.devland_api.enums.FileType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "material")
@Getter
@Setter
public class MaterialModel {

    @Id
    private String materialId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "file_type", nullable = false)
    private FileType fileType;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @Column(name = "is_public", nullable = false)
    private boolean isPublic;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private CourseModuleModel courseModule;
}