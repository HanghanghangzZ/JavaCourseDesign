package com.hang.toilethrmanagement.mapper;

import com.hang.toilethrmanagement.model.Performance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PerformanceMapper {

    List<Performance> getPerformanceList(String query, int pageNum, int pageSize);

    int countPerformance(String query);

    int addPerformance(Performance performance);

    List<Performance> getPerformanceListByYM(String ym);

}
