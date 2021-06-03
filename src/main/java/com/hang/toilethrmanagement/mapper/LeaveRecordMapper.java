package com.hang.toilethrmanagement.mapper;

import com.hang.toilethrmanagement.model.LeaveRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeaveRecordMapper {

    List<LeaveRecord> getLeaveRecordList(String query, int offset, int pageSize);

    int countLeaveRecord(String query);

    int addLeaveRecord(LeaveRecord leaveRecord);

    int updateLeaveRecordById(LeaveRecord leaveRecord);

    LeaveRecord queryLeaveRecordById(Integer id);
}
