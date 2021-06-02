package com.hang.toilethrmanagement.controller;

import com.hang.toilethrmanagement.dto.TrainingDTO;
import com.hang.toilethrmanagement.enums.CRUDMsgEnum;
import com.hang.toilethrmanagement.mapper.TrainingMapper;
import com.hang.toilethrmanagement.model.Qualification;
import com.hang.toilethrmanagement.model.Training;
import com.hang.toilethrmanagement.service.TrainingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "员工培训模块")
@RestController
@CrossOrigin
public class TrainingController {

    private TrainingService trainingService;

    @Autowired
    public void setTrainingService(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @ApiOperation("获取员工培训数据")
    @GetMapping(value = {"/training/{query}/{pageNum}/{pageSize}", "/training/{pageNum}/{pageSize}"})
    public Map<String, Object> queryTraining(@PathVariable(required = false) String query,
                                             @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        HashMap<String, Object> result = new HashMap<>();

        List<TrainingDTO> trainingDTOList = trainingService.getTrainingDTOList(query, pageNum, pageSize);

        int totalCount = trainingService.countTraining(query);

        result.put("totalCount", totalCount);
        result.put("trainingDTOList", trainingDTOList);
        result.put("pageNum", pageNum);
        result.put("status", 200);
        result.put("msg", CRUDMsgEnum.SELECT_SUCCESS);

        return result;
    }

    @ApiOperation("添加员工培训")
    @PostMapping("/training")
    public Map<String, Object> addTraining(@RequestBody Training training) {
        HashMap<String, Object> result = new HashMap<>();
        int i = trainingService.addTraining(training);
        if (i > 0) {
            result.put("status", 200);
            result.put("msg", CRUDMsgEnum.INSERT_SUCCESS);
        } else {
            result.put("status", 500);
            result.put("msg", CRUDMsgEnum.INSERT_FAIL);
        }
        return result;
    }

    @ApiOperation("删除员工培训")
    @DeleteMapping("/training/{id}")
    public Map<String, Object> deleteTrainingById(@PathVariable Integer id) {
        HashMap<String, Object> result = new HashMap<>();
        int i = trainingService.deleteTrainingById(id);
        if (i > 0) {
            result.put("status", 200);
            result.put("msg", CRUDMsgEnum.DELETE_SUCCESS);
        } else {
            result.put("status", 500);
            result.put("msg", CRUDMsgEnum.DELETE_FAIL);
        }
        return result;
    }
}
