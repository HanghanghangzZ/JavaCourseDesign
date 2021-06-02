package com.hang.toilethrmanagement.service;

import com.hang.toilethrmanagement.dto.LeaveRecordDTO;
import com.hang.toilethrmanagement.exception.CustomizeErrorCode;
import com.hang.toilethrmanagement.exception.CustomizeException;
import com.hang.toilethrmanagement.mapper.LeaveRecordMapper;
import com.hang.toilethrmanagement.mapper.StaffMapper;
import com.hang.toilethrmanagement.model.LeaveRecord;
import com.hang.toilethrmanagement.model.Staff;
import com.hang.toilethrmanagement.utils.TimestampDiff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveRecordService {

    private LeaveRecordMapper leaveRecordMapper;

    @Autowired
    public void setLeaveRecordMapper(LeaveRecordMapper leaveRecordMapper) {
        this.leaveRecordMapper = leaveRecordMapper;
    }

    private StaffMapper staffMapper;

    @Autowired
    public void setStaffMapper(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }

    @Transactional
    public List<LeaveRecordDTO> getLeaveRecordDTOList(String query, int pageNum, int pageSize) {
        List<LeaveRecord> leaveRecordList = leaveRecordMapper.getLeaveRecordList(query, pageNum, pageSize);

        List<LeaveRecordDTO> leaveRecordDTOList = leaveRecordList.stream().map(leaveRecord -> {
            String staffName = staffMapper.getStaffNameById(leaveRecord.getStaffId());
            String approcverName = staffMapper.getStaffNameById(leaveRecord.getApprocverId());

            LeaveRecordDTO leaveRecordDTO = new LeaveRecordDTO(leaveRecord, staffName, approcverName);

            return leaveRecordDTO;
        }).collect(Collectors.toList());

        return leaveRecordDTOList;
    }

    public int countLeaveRecord(String query) {

        return leaveRecordMapper.countLeaveRecord(query);
    }

    @Transactional
    public int updateLeaveRecordById(LeaveRecord leaveRecord) {
        LeaveRecord dbLeaveRecord = leaveRecordMapper.queryLeaveRecordById(leaveRecord.getId());
        /* 已审批的不能再改成未审批 */
        if (("通过".equals(dbLeaveRecord.getPass()) || "未通过".equals(dbLeaveRecord.getPass()))
                && "0".equals(leaveRecord.getPass())) {
            return 0;
        }
        if ("1".equals(leaveRecord.getPass())) {
            leaveRecord.setPass("通过");
        } else if ("2".equals(leaveRecord.getPass())) {
            leaveRecord.setPass("未通过");
        } else {
            throw new CustomizeException(CustomizeErrorCode.PARAMETER_ERROR);
        }
        int i = leaveRecordMapper.updateLeaveRecordById(leaveRecord);

        return i;
    }

    public int addLeaveRecord(LeaveRecord leaveRecord) {
        String lastTime = TimestampDiff.diffToTimeStamp(leaveRecord.getLeaveEndTime() - leaveRecord.getLeaveStartTime());
        leaveRecord.setLastTime(lastTime);

        return leaveRecordMapper.addLeaveRecord(leaveRecord);
    }
}
