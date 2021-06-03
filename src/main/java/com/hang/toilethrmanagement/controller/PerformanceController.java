package com.hang.toilethrmanagement.controller;

import com.hang.toilethrmanagement.bean.Pagination;
import com.hang.toilethrmanagement.dto.PerformanceDTO;
import com.hang.toilethrmanagement.enums.CRUDMsgEnum;
import com.hang.toilethrmanagement.model.Performance;
import com.hang.toilethrmanagement.service.PerformanceService;
import com.hang.toilethrmanagement.utils.PaginationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "绩效模块")
@RestController
@CrossOrigin
public class PerformanceController {

    private PerformanceService performanceService;

    @Autowired
    public void setPerformanceService(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @ApiOperation("获取绩效数据")
    @GetMapping(value = {"/performance/{query}/{pageNum}/{pageSize}", "/performance/{pageNum}/{pageSize}"})
    public Map<String, Object> queryPerformance(@PathVariable(required = false) String query,
                                                @PathVariable int pageNum, @PathVariable int pageSize) {
        HashMap<String, Object> result = new HashMap<>();

        int totalCount = performanceService.countPerformance(query);

        Pagination pagination = PaginationUtil.getPagination(totalCount, pageSize, pageNum);
        pageNum = pagination.getPageNum();

        List<PerformanceDTO> performanceDTOList = performanceService.getPerformanceDTOList(query, pagination.getOffset(), pageSize);

        result.put("totalPage", pagination.getTotalPage());
        result.put("totalCount", totalCount);
        result.put("performanceDTOList", performanceDTOList);
        result.put("pageNum", pageNum);
        result.put("status", 200);
        result.put("msg", CRUDMsgEnum.SELECT_SUCCESS);

        return result;
    }

    @ApiOperation("添加绩效")
    @PostMapping("/performance")
    public Map<String, Object> addPerformance(@RequestBody Performance performance) {
        HashMap<String, Object> result = new HashMap<>();
        int i = performanceService.addPerformance(performance);
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
