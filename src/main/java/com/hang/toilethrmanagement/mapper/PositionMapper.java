package com.hang.toilethrmanagement.mapper;

import com.hang.toilethrmanagement.model.Position;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PositionMapper {

    List<Position> getPositionList(String query, Integer pageNum, Integer pageSize);

    int countPosition(String query);

    int addPosition(Position position);

    int updatePositionById(Position position);

    int deletePositionById(Integer id);

    Position getPositionById(Integer positionId);

    int addCountById(Integer positionId);
}
