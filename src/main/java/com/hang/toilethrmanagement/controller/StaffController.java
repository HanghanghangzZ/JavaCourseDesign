package com.hang.toilethrmanagement.controller;

import com.hang.toilethrmanagement.dto.StaffDTO;
import com.hang.toilethrmanagement.model.Staff;
import com.hang.toilethrmanagement.enums.CRUDMsgEnum;
import com.hang.toilethrmanagement.service.StaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "员工模块")
@RestController
@CrossOrigin
public class StaffController {

    private StaffService staffService;

    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }

    @ApiOperation("获取员工数据")
    @GetMapping(value = {"/staff/{query}/{pageNum}/{pageSize}", "/staff/{pageNum}/{pageSize}"})
    public Map<String, Object> queryStaff(@PathVariable(required = false) String query,
                                          @PathVariable int pageNum, @PathVariable int pageSize) {
        HashMap<String, Object> result = new HashMap<>();

        List<StaffDTO> staffDTOList = staffService.getStaffList(query, pageNum, pageSize);

        int totalCount = staffService.countStaff(query);

        result.put("totalCount", totalCount);
        result.put("staffDTOList", staffDTOList);
        result.put("pageNum", pageNum);
        result.put("status", 200);
        result.put("msg", CRUDMsgEnum.SELECT_SUCCESS);

        return result;
    }

    @ApiOperation("更新员工数据")
    @PutMapping("/staff")
    public Map<String, Object> updateStaffById(@RequestBody Staff staff) {
        HashMap<String, Object> result = new HashMap<>();
        int i = staffService.updateStaffById(staff);
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
