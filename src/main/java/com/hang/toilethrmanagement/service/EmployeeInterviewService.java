package com.hang.toilethrmanagement.service;

import com.hang.toilethrmanagement.dto.EmployeeInterviewPass;
import com.hang.toilethrmanagement.enums.InterviewStatusEnum;
import com.hang.toilethrmanagement.exception.CustomizeErrorCode;
import com.hang.toilethrmanagement.exception.CustomizeException;
import com.hang.toilethrmanagement.mapper.EmployeeInterviewMapper;
import com.hang.toilethrmanagement.mapper.PositionMapper;
import com.hang.toilethrmanagement.mapper.QualificationMapper;
import com.hang.toilethrmanagement.mapper.StaffMapper;
import com.hang.toilethrmanagement.model.EmployeeInterview;
import com.hang.toilethrmanagement.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeInterviewService {

    private EmployeeInterviewMapper employeeInterviewMapper;

    @Autowired
    public void setEmployeeInterviewMapper(EmployeeInterviewMapper employeeInterviewMapper) {
        this.employeeInterviewMapper = employeeInterviewMapper;
    }

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

    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ---------------------------------------------------------------------------------------------------------------- */
    /* ---------------------------------------------------------------------------------------------------------------- */

    public List<EmployeeInterview> getEmployeeInterviewList(String query, int offset, int pageSize) {

        return employeeInterviewMapper.getEmployeeInterviewList(query, offset, pageSize);
    }

    public int countEmployeeInterview(String query) {

        return employeeInterviewMapper.countEmployeeInterview(query);
    }

    public int addEmployeeInterview(EmployeeInterview employeeInterview) {
        /* 前端传参错误 */
        if (!InterviewStatusEnum.containStatus(employeeInterview.getInterviewStatus())) {
            throw new CustomizeException(CustomizeErrorCode.PARAMETER_ERROR);
        }

        String indexToStatus = InterviewStatusEnum.indexToStatus(employeeInterview.getInterviewStatus());
        employeeInterview.setInterviewStatus(indexToStatus);

        return employeeInterviewMapper.addEmployeeInterview(employeeInterview);
    }

    public int updateEmployeeInterviewById(EmployeeInterview employeeInterview) {
        if (InterviewStatusEnum.containStatus(employeeInterview.getInterviewStatus())) {
            String indexToStatus = InterviewStatusEnum.indexToStatus(employeeInterview.getInterviewStatus());
            employeeInterview.setInterviewStatus(indexToStatus);
        } else {
            throw new CustomizeException(CustomizeErrorCode.PARAMETER_ERROR);
        }

        return employeeInterviewMapper.updateEmployeeInterviewById(employeeInterview);
    }

    @Transactional
    public int employeeInterviewPass(EmployeeInterviewPass employeeInterviewPass) {
        Staff staff = EmployeeInterviewPass.passToStaff(employeeInterviewPass);

        /* 在员工面试表中更新为面试通过 */
        EmployeeInterview employeeInterview = employeeInterviewMapper.getEmployeeInterviewById(employeeInterviewPass.getId());
        if (InterviewStatusEnum.isPass(employeeInterview) || InterviewStatusEnum.isFail(employeeInterview)) {
            throw new CustomizeException(CustomizeErrorCode.PARAMETER_ERROR);
        }
        employeeInterviewMapper.pass(employeeInterview.getId());

        /* 将通过面试的人员插入到员工表中 */
        int i = staffMapper.insertStaff(staff);
        /* 同时增加职位和资质的统计 */
        qualificationMapper.addCountById(staff.getQualificationId());
        positionMapper.addCountById(staff.getPositionId());

        return i;
    }
}
