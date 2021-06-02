package com.hang.toilethrmanagement.service;

import com.hang.toilethrmanagement.dto.PerformanceDTO;
import com.hang.toilethrmanagement.mapper.PerformanceMapper;
import com.hang.toilethrmanagement.mapper.StaffMapper;
import com.hang.toilethrmanagement.model.Performance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerformanceService {

    private PerformanceMapper performanceMapper;

    @Autowired
    public void setPerformanceMapper(PerformanceMapper performanceMapper) {
        this.performanceMapper = performanceMapper;
    }

    private StaffMapper staffMapper;

    @Autowired
    public void setStaffMapper(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }

    @Transactional
    public List<PerformanceDTO> getPerformanceDTOList(String query, int pageNum, int pageSize) {

        List<Performance> performanceList = performanceMapper.getPerformanceList(query, pageNum, pageSize);

        List<PerformanceDTO> performanceDTOList = performanceList.stream().map(performance -> {
            String staffName = staffMapper.getStaffNameById(performance.getStaffId());

            PerformanceDTO performanceDTO = new PerformanceDTO(performance, staffName);

            return performanceDTO;
        }).collect(Collectors.toList());

        return performanceDTOList;
    }

    public int countPerformance(String query) {

        return performanceMapper.countPerformance(query);
    }

    public int addPerformance(Performance performance) {

        return performanceMapper.addPerformance(performance);
    }
}
