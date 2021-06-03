package com.hang.toilethrmanagement.controller;

import com.hang.toilethrmanagement.bean.Pagination;
import com.hang.toilethrmanagement.dto.LeaveRecordDTO;
import com.hang.toilethrmanagement.enums.CRUDMsgEnum;
import com.hang.toilethrmanagement.model.LeaveRecord;
import com.hang.toilethrmanagement.service.LeaveRecordService;
import com.hang.toilethrmanagement.utils.PaginationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "请假记录模块")
@RestController
@CrossOrigin
public class LeaveRecordController {

    private LeaveRecordService leaveRecordService;

    @Autowired
    public void setLeaveRecordService(LeaveRecordService leaveRecordService) {
        this.leaveRecordService = leaveRecordService;
    }

    @ApiOperation("获取请假数据")
    @GetMapping(value = {"/leaveRecord/{query}/{pageNum}/{pageSize}", "/leaveRecord/{pageNum}/{pageSize}"})
    public Map<String, Object> queryLeaveRecord(@PathVariable(required = false) String query,
                                                @PathVariable int pageNum, @PathVariable int pageSize) {
        HashMap<String, Object> result = new HashMap<>();

        int totalCount = leaveRecordService.countLeaveRecord(query);

        Pagination pagination = PaginationUtil.getPagination(totalCount, pageSize, pageNum);
        pageNum = pagination.getPageNum();

        List<LeaveRecordDTO> leaveRecordDTOList = leaveRecordService.getLeaveRecordDTOList(query, pagination.getOffset(), pageSize);

        result.put("totalPage", pagination.getTotalPage());
        result.put("totalCount", totalCount);
        result.put("leaveRecordDTOList", leaveRecordDTOList);
        result.put("pageNum", pageNum);
        result.put("status", 200);
        result.put("msg", CRUDMsgEnum.SELECT_SUCCESS);

        return result;
    }

    @ApiOperation("添加请假记录")
    @PostMapping("/leaveRecord")
    public Map<String, Object> addLeaveRecord(@RequestBody LeaveRecord leaveRecord) {
        HashMap<String, Object> result = new HashMap<>();
        int i = leaveRecordService.addLeaveRecord(leaveRecord);
        if (i > 0) {
            result.put("status", 200);
            result.put("msg", CRUDMsgEnum.INSERT_SUCCESS);
        } else {
            result.put("status", 500);
            result.put("msg", CRUDMsgEnum.INSERT_FAIL);
        }
        return result;
    }

    @ApiOperation("更新请假记录")
    @PutMapping("/leaveRecord")
    public Map<String, Object> updateLeaveRecordById(@RequestBody LeaveRecord leaveRecord) {
        HashMap<String, Object> result = new HashMap<>();
        int i = leaveRecordService.updateLeaveRecordById(leaveRecord);
        if (i > 0) {
            result.put("status", 200);
            result.put("msg", CRUDMsgEnum.UPDATE_SUCCESS);
        } else {
            result.put("status", 500);
            result.put("msg", CRUDMsgEnum.UPDATE_FAIL);
        }

        return result;
    }
}
