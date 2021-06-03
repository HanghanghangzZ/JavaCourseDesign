package com.hang.toilethrmanagement.controller;

import com.hang.toilethrmanagement.bean.Pagination;
import com.hang.toilethrmanagement.enums.CRUDMsgEnum;
import com.hang.toilethrmanagement.mapper.PositionMapper;
import com.hang.toilethrmanagement.model.Position;
import com.hang.toilethrmanagement.utils.PaginationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "职位模块")
@RestController
@CrossOrigin
public class PositionController {

    private PositionMapper positionMapper;

    @Autowired
    public void setPositionMapper(PositionMapper positionMapper) {
        this.positionMapper = positionMapper;
    }

    @ApiOperation("获取职位数据")
    @GetMapping(value = {"/position/{query}/{pageNum}/{pageSize}", "/position/{pageNum}/{pageSize}"})
    public Map<String, Object> queryPosition(@PathVariable(required = false) String query,
                                             @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        HashMap<String, Object> result = new HashMap<>();

        int totalCount = positionMapper.countPosition(query);

        Pagination pagination = PaginationUtil.getPagination(totalCount, pageSize, pageNum);
        pageNum = pagination.getPageNum();

        List<Position> positionList = positionMapper.getPositionList(query, pagination.getOffset(), pageSize);

        result.put("totalPage", pagination.getTotalPage());
        result.put("totalCount", totalCount);
        result.put("positionList", positionList);
        result.put("pageNum", pageNum);
        result.put("status", 200);
        result.put("msg", CRUDMsgEnum.SELECT_SUCCESS);

        return result;
    }

    @ApiOperation("添加新职位")
    @PostMapping("/position")
    public Map<String, Object> addPosition(@RequestBody Position position) {
        HashMap<String, Object> result = new HashMap<>();
        int i = positionMapper.addPosition(position);
        if (i > 0) {
            result.put("status", 200);
            result.put("msg", CRUDMsgEnum.INSERT_SUCCESS);
        } else {
            result.put("status", 500);
            result.put("msg", CRUDMsgEnum.INSERT_FAIL);
        }
        return result;
    }

    @ApiOperation("更新职位")
    @PutMapping("/position")
    public Map<String, Object> updatePositionById(@RequestBody Position position) {
        HashMap<String, Object> result = new HashMap<>();
        int i = positionMapper.updatePositionById(position);
        if (i > 0) {
            result.put("status", 200);
            result.put("msg", CRUDMsgEnum.UPDATE_SUCCESS);
        } else {
            result.put("status", 500);
            result.put("msg", CRUDMsgEnum.UPDATE_FAIL);
        }

        return result;
    }

    @ApiOperation("删除职位")
    @DeleteMapping("/position/{id}")
    public Map<String, Object> deletePositionById(@PathVariable Integer id) {
        HashMap<String, Object> result = new HashMap<>();
        int i = positionMapper.deletePositionById(id);
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
