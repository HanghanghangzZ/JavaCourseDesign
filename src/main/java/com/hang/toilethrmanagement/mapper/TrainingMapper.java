package com.hang.toilethrmanagement.mapper;

import com.hang.toilethrmanagement.model.Training;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TrainingMapper {

    List<Training> getTrainingList(String query, Integer offset, Integer pageSize);

    int countTraining(String query);

    int addTraining(Training training);

    int deleteTrainingById(Integer id);
}
