package com.hang.toilethrmanagement.controller;

import com.hang.toilethrmanagement.bean.Pagination;
import com.hang.toilethrmanagement.dto.EmployeeInterviewPass;
import com.hang.toilethrmanagement.enums.CRUDMsgEnum;
import com.hang.toilethrmanagement.enums.InterviewStatusEnum;
import com.hang.toilethrmanagement.mapper.EmployeeInterviewMapper;
import com.hang.toilethrmanagement.mapper.StaffMapper;
import com.hang.toilethrmanagement.model.EmployeeInterview;
import com.hang.toilethrmanagement.model.Staff;
import com.hang.toilethrmanagement.service.EmployeeInterviewService;
import com.hang.toilethrmanagement.utils.PaginationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "员工面试模块")
@RestController
@CrossOrigin
public class EmployeeInterviewController {

    private EmployeeInterviewService employeeInterviewService;

    @Autowired
    public void setEmployeeInterviewService(EmployeeInterviewService employeeInterviewService) {
        this.employeeInterviewService = employeeInterviewService;
    }

    private StaffMapper staffMapper;

    @Autowired
    public void setStaffMapper(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }

    @ApiOperation("获取员工面试数据")
    @GetMapping(value = {"/employeeInterview/{query}/{pageNum}/{pageSize}", "/employeeInterview/{pageNum}/{pageSize}"})
    public Map<String, Object> queryEmployeeInterview(@PathVariable(required = false) String query,
                                                      @PathVariable int pageNum, @PathVariable int pageSize) {
        HashMap<String, Object> result = new HashMap<>();

        int totalCount = employeeInterviewService.countEmployeeInterview(query);

        Pagination pagination = PaginationUtil.getPagination(totalCount, pageSize, pageNum);
        pageNum = pagination.getPageNum();

        List<EmployeeInterview> employeeInterviewList = employeeInterviewService.getEmployeeInterviewList(query, pagination.getOffset(), pageSize);

        result.put("totalCount", totalCount);
        result.put("totalPage", pagination.getTotalPage());
        result.put("employeeInterviewList", employeeInterviewList);
        result.put("pageNum", pageNum);
        result.put("status", 200);
        result.put("msg", CRUDMsgEnum.SELECT_SUCCESS);

        return result;
    }

    @ApiOperation("添加员工面试")
    @PostMapping("/employeeInterview")
    public Map<String, Object> addEmployeeInterview(@RequestBody EmployeeInterview employeeInterview) {
        HashMap<String, Object> result = new HashMap<>();
        int i = employeeInterviewService.addEmployeeInterview(employeeInterview);
        if (i > 0) {
            result.put("status", 200);
            result.put("msg", CRUDMsgEnum.INSERT_SUCCESS);
        } else {
            result.put("status", 500);
            result.put("msg", CRUDMsgEnum.INSERT_FAIL);
        }
        return result;
    }

    @ApiOperation("更新员工面试")
    @PutMapping("/employeeInterview")
    public Map<String, Object> updateEmployeeInterviewById(@RequestBody EmployeeInterview employeeInterview) {
        HashMap<String, Object> result = new HashMap<>();

        int i = employeeInterviewService.updateEmployeeInterviewById(employeeInterview);
        if (i > 0) {
            result.put("status", 200);
            result.put("msg", CRUDMsgEnum.UPDATE_SUCCESS);
        } else {
            result.put("status", 500);
            result.put("msg", CRUDMsgEnum.UPDATE_FAIL);
        }

        return result;
    }

    @ApiOperation("员工面试通过转换为正式员工")
    @PostMapping("/employeeInterviewPass")
    public Map<String, Object> employeeInterviewPass(@RequestBody EmployeeInterviewPass employeeInterviewPass) {
        HashMap<String, Object> result = new HashMap<>();

        int i = employeeInterviewService.employeeInterviewPass(employeeInterviewPass);

        if (i > 0) {
            result.put("status", 200);
            result.put("msg", CRUDMsgEnum.INSERT_SUCCESS);
        } else {
            result.put("status", 500);
            result.put("msg", CRUDMsgEnum.INSERT_FAIL);
        }

        return result;
    }
}