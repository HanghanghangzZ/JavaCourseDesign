package com.hang.toilethrmanagement.controller;

import com.hang.toilethrmanagement.dto.PayrollDTO;
import com.hang.toilethrmanagement.enums.CRUDMsgEnum;
import com.hang.toilethrmanagement.service.PayrollService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "工资单模块")
@RestController
@CrossOrigin
public class PayrollController {

    private PayrollService payrollService;

    @Autowired
    public void setPayrollService(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @ApiOperation("获取工资单数据")
    @Transactional
    @GetMapping(value = {"/payRoll/{query}/{pageNum}/{pageSize}", "/payRoll/{pageNum}/{pageSize}"})
    public Map<String, Object> queryPayroll(@PathVariable(required = false) String query,
                                            @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        HashMap<String, Object> result = new HashMap<>();

        List<PayrollDTO> payrollDTOList = payrollService.getPayrollDTOList(query, pageNum, pageSize);

        int totalCount = payrollService.countPayroll(query);

        result.put("totalCount", totalCount);
        result.put("payRollDTOList", payrollDTOList);
        result.put("pageNum", pageNum);
        result.put("status", 200);
        result.put("msg", CRUDMsgEnum.SELECT_SUCCESS);

        return result;
    }

}
