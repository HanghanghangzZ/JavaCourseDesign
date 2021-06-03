package com.hang.toilethrmanagement.controller;

import com.hang.toilethrmanagement.bean.Pagination;
import com.hang.toilethrmanagement.dto.AttendanceDTO;
import com.hang.toilethrmanagement.enums.CRUDMsgEnum;
import com.hang.toilethrmanagement.model.Attendance;
import com.hang.toilethrmanagement.service.AttendanceService;
import com.hang.toilethrmanagement.utils.PaginationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "考勤模块")
@RestController
@CrossOrigin
public class AttendanceController {

    private AttendanceService attendanceService;

    @Autowired
    public void setAttendanceService(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @ApiOperation("获取考勤数据")
    @GetMapping(value = {"/attendance/{query}/{pageNum}/{pageSize}", "/attendance/{pageNum}/{pageSize}"})
    public Map<String, Object> queryAttendance(@PathVariable(required = false) String query,
                                               @PathVariable int pageNum, @PathVariable int pageSize) {
        HashMap<String, Object> result = new HashMap<>();

        int totalCount = attendanceService.countAttendance(query);

        Pagination pagination = PaginationUtil.getPagination(totalCount, pageSize, pageNum);
        pageNum = pagination.getPageNum();

        List<AttendanceDTO> attendanceDTOList = attendanceService.getAttendanceDTOList(query, pagination.getOffset(), pageSize);

        result.put("totalCount", totalCount);
        result.put("totalPage", pagination.getTotalPage());
        result.put("attendanceDTOList", attendanceDTOList);
        result.put("pageNum", pageNum);
        result.put("status", 200);
        result.put("msg", CRUDMsgEnum.SELECT_SUCCESS);

        return result;
    }

    @ApiOperation("添加考勤")
    @PostMapping("/attendance")
    public Map<String, Object> addAttendance(@RequestBody Attendance attendance) {
        HashMap<String, Object> result = new HashMap<>();
        int i = attendanceService.addAttendance(attendance);
        if (i > 0) {
            result.put("status", 200);
            result.put("msg", CRUDMsgEnum.INSERT_SUCCESS);
        } else {
            result.put("status", 500);
            result.put("msg", CRUDMsgEnum.INSERT_FAIL);
        }
        return result;
    }

    @ApiOperation("更新考勤")
    @PutMapping("/attendance")
    public Map<String, Object> updateAttendanceById(@RequestBody Attendance attendance) {
        HashMap<String, Object> result = new HashMap<>();
        int i = attendanceService.updateAttendanceById(attendance);
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
