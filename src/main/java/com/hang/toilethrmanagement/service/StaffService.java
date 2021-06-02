package com.hang.toilethrmanagement.service;

import com.hang.toilethrmanagement.dto.StaffDTO;
import com.hang.toilethrmanagement.mapper.PositionMapper;
import com.hang.toilethrmanagement.mapper.QualificationMapper;
import com.hang.toilethrmanagement.mapper.StaffMapper;
import com.hang.toilethrmanagement.model.Position;
import com.hang.toilethrmanagement.model.Qualification;
import com.hang.toilethrmanagement.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffService {

    private StaffMapper staffMapper;

    @Autowired
    public void setStaffMapper(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }

    private PositionMapper positionMapper;

    @Autowired
    public void setPositionMapper(PositionMapper positionMapper) {
        this.positionMapper = positionMapper;
    }

    private QualificationMapper qualificationMapper;

    @Autowired
    public void setQualificationMapper(QualificationMapper qualificationMapper) {
        this.qualificationMapper = qualificationMapper;
    }

    @Transactional
    public List<StaffDTO> getStaffList(String query, int pageNum, int pageSize) {
        List<Staff> staffList = staffMapper.getStaffList(query, pageNum, pageSize);

        List<StaffDTO> staffDTOList = staffList.stream().map(staff -> {
            Position position = positionMapper.getPositionById(staff.getPositionId());
            Qualification qualification = qualificationMapper.getQualificationById(staff.getQualificationId());

            StaffDTO staffDTO = new StaffDTO(staff, position.getName(), qualification.getName());

            return staffDTO;
        }).collect(Collectors.toList());

        return staffDTOList;
    }

    public int countStaff(String query) {

        return staffMapper.countStaff(query);
    }

    public int updateStaffById(Staff staff) {

        return staffMapper.updateStaffById(staff);
    }
}
