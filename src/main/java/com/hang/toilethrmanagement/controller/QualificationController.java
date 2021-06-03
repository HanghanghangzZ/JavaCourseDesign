package com.hang.toilethrmanagement.controller;

import com.hang.toilethrmanagement.bean.Pagination;
import com.hang.toilethrmanagement.enums.CRUDMsgEnum;
import com.hang.toilethrmanagement.mapper.QualificationMapper;
import com.hang.toilethrmanagement.model.Qualification;
import com.hang.toilethrmanagement.utils.PaginationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "资质模块")
@RestController
@CrossOrigin
public class QualificationController {

    private QualificationMapper qualificationMapper;

    @Autowired
    public void setQualificationMapper(QualificationMapper qualificationMapper) {
        this.qualificationMapper = qualificationMapper;
    }

    @ApiOperation("获取资质数据")
    @GetMapping(value = {"/qualification/{query}/{pageNum}/{pageSize}", "/qualification/{pageNum}/{pageSize}"})
    public Map<String, Object> queryQualification(@PathVariable(required = false) String query,
                                                  @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        HashMap<String, Object> result = new HashMap<>();

        int totalCount = qualificationMapper.countQualification(query);

        Pagination pagination = PaginationUtil.getPagination(totalCount, pageSize, pageNum);
        pageNum = pagination.getPageNum();

        List<Qualification> qualificationList = qualificationMapper.getQualificationList(query, pagination.getOffset(), pageSize);

        result.put("totalPage", pagination.getTotalPage());
        result.put("totalCount", totalCount);
        result.put("qualificationList", qualificationList);
        result.put("pageNum", pageNum);
        result.put("status", 200);
        result.put("msg", CRUDMsgEnum.SELECT_SUCCESS);

        return result;
    }

    @ApiOperation("添加资质")
    @PostMapping("/qualification")
    public Map<String, Object> addQualification(@RequestBody Qualification qualification) {
        HashMap<String, Object> result = new HashMap<>();
        int i = qualificationMapper.addQualification(qualification);
        if (i > 0) {
            result.put("status", 200);
            result.put("msg", CRUDMsgEnum.INSERT_SUCCESS);
        } else {
            result.put("status", 500);
            result.put("msg", CRUDMsgEnum.INSERT_FAIL);
        }
        return result;
    }

    @ApiOperation("更新资质")
    @PutMapping("/qualification")
    public Map<String, Object> updateQualificationById(@RequestBody Qualification qualification) {
        HashMap<String, Object> result = new HashMap<>();
        int i = qualificationMapper.updateQualificationById(qualification);
        if (i > 0) {
            result.put("status", 200);
            result.put("msg", CRUDMsgEnum.UPDATE_SUCCESS);
        } else {
            result.put("status", 500);
            result.put("msg", CRUDMsgEnum.UPDATE_FAIL);
        }

        return result;
    }

    @ApiOperation("删除资质")
    @DeleteMapping("/qualification/{id}")
    public Map<String, Object> deleteQualificationById(@PathVariable Integer id) {
        HashMap<String, Object> result = new HashMap<>();
        int i = qualificationMapper.deleteQualificationById(id);
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
