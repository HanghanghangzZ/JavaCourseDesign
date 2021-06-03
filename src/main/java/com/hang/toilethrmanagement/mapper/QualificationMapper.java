package com.hang.toilethrmanagement.mapper;

import com.hang.toilethrmanagement.model.Qualification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QualificationMapper {

    List<Qualification> getQualificationList(String query, Integer offset, Integer pageSize);

    int countQualification(String query);

    int addQualification(Qualification qualification);

    int updateQualificationById(Qualification qualification);

    int deleteQualificationById(Integer id);

    Qualification getQualificationById(Integer qualificationId);

    int addCountById(Integer qualificationId);
}
