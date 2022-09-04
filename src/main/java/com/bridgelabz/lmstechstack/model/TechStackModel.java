package com.bridgelabz.lmstechstack.model;

import com.bridgelabz.lmstechstack.dto.TechStackDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name ="teckstack")
public class TechStackModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String techName;
    private String email;
    private String imagePath;
    private String status;
    private LocalDateTime creationTimeStamp;
    private LocalDateTime updatedTimeStamp;

    public TechStackModel(TechStackDTO techStackDTO) {
        this.imagePath = techStackDTO.getImagePath();
        this.status = techStackDTO.getStatus();
        this.techName = techStackDTO.getTechName();
        this.email=techStackDTO.getEmail();
    }

    public TechStackModel() {

    }
}
