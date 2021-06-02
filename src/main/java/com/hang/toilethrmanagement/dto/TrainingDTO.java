package com.hang.toilethrmanagement.dto;

import com.hang.toilethrmanagement.model.Training;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TrainingDTO {
    private Integer id;
    private String trainingMethod;
    private BigDecimal score;
    private Long startTime;
    private Long endTime;
    private String lastTime;

    private String staffName;

    public TrainingDTO() {
    }

    public TrainingDTO(Training training, String staffName) {
        this.staffName = staffName;

        this.id = training.getId();
        this.trainingMethod = training.getTrainingMethod();
        this.score = training.getScore();
        this.startTime = training.getStartTime();
        this.endTime = training.getEndTime();
        this.lastTime = training.getLastTime();
    }
}
