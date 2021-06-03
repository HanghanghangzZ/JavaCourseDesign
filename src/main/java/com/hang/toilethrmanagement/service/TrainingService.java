package com.hang.toilethrmanagement.service;

import com.hang.toilethrmanagement.dto.TrainingDTO;
import com.hang.toilethrmanagement.mapper.StaffMapper;
import com.hang.toilethrmanagement.mapper.TrainingMapper;
import com.hang.toilethrmanagement.model.Training;
import com.hang.toilethrmanagement.utils.TimestampDiff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingService {

    private TrainingMapper trainingMapper;

    @Autowired
    public void setTrainingMapper(TrainingMapper trainingMapper) {
        this.trainingMapper = trainingMapper;
    }

    private StaffMapper staffMapper;

    @Autowired
    public void setStaffMapper(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }

    @Transactional
    public List<TrainingDTO> getTrainingDTOList(String query, Integer offset, Integer pageSize) {
        List<Training> trainingList = trainingMapper.getTrainingList(query, offset, pageSize);

        List<TrainingDTO> trainingDTOList = trainingList.stream().map(training -> {
            String staffNameById = staffMapper.getStaffNameById(training.getStaffId());

            TrainingDTO trainingDTO = new TrainingDTO(training, staffNameById);

            return trainingDTO;
        }).collect(Collectors.toList());

        return trainingDTOList;
    }

    public int countTraining(String query) {

        return trainingMapper.countTraining(query);
    }

    public int addTraining(Training training) {
        String lastTime = TimestampDiff.diffToTimeStamp(training.getEndTime() - training.getStartTime());
        training.setLastTime(lastTime);

        return trainingMapper.addTraining(training);
    }

    public int deleteTrainingById(Integer id) {

        return trainingMapper.deleteTrainingById(id);
    }
}
