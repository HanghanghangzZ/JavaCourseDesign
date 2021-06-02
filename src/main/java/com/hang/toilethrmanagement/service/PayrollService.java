package com.hang.toilethrmanagement.service;

import com.hang.toilethrmanagement.dto.PayrollDTO;
import com.hang.toilethrmanagement.mapper.*;
import com.hang.toilethrmanagement.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PayrollService {

    private PayrollMapper payrollMapper;

    @Autowired
    public void setPayrollMapper(PayrollMapper payrollMapper) {
        this.payrollMapper = payrollMapper;
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

    private PerformanceMapper performanceMapper;

    @Autowired
    public void setPerformanceMapper(PerformanceMapper performanceMapper) {
        this.performanceMapper = performanceMapper;
    }
    /* ----------------------------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------------------------- */

    @Transactional
    public List<PayrollDTO> getPayrollDTOList(String query, Integer pageNum, Integer pageSize) {
        List<Payroll> payrollList = payrollMapper.getPayrollList(query, pageNum, pageSize);

        List<PayrollDTO> payrollDTOList = payrollList.stream().map(payroll -> {
            String staffNameById = staffMapper.getStaffNameById(payroll.getStaffId());
            PayrollDTO payrollDTO = new PayrollDTO(payroll, staffNameById);

            return payrollDTO;
        }).collect(Collectors.toList());

        return payrollDTOList;
    }

    public int countPayroll(String query) {

        return payrollMapper.countPayroll();
    }

    /* 每个月1号的凌晨2点生成工资单 */
    @Scheduled(cron = "0 0 2 1 * ?")
    /* 测试用，一分钟执行一次 */
//    @Scheduled(cron = "0 0/1 * * * ?")
    @Transactional
    public void generatePayroll() {
        /* 1.自动生成工资单，我们需要所有员工 */
        List<Staff> staffList = staffMapper.getStaffList(null, 0, staffMapper.countStaff(null));

        /* 2.需要知道这个员工的职位，职位基本工资 */
        List<Position> positionList = positionMapper.getPositionList(null, 0, positionMapper.countPosition(null));
        Map<Integer, BigDecimal> positionMap = positionList.stream().collect(Collectors.toMap(Position::getId, Position::getBasicWage));

        /* 3.需要知道这个员工的资质，资质额外工资 */
        List<Qualification> qualificationList = qualificationMapper.getQualificationList(null, 0, qualificationMapper.countQualification(null));
        Map<Integer, BigDecimal> qualificationMap = qualificationList.stream().collect(Collectors.toMap(Qualification::getId, Qualification::getExtraSalary));

        /* 4.需要知道这个员工的绩效，绩效奖金，并选择指定年月的绩效 */
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        /* 在月初统计工资，那么统计的应该是上个月的绩效 */
        if (month == 1) {
            month = 12;
            year -= 1;
        } else {
            month -= 1;
        }
        String m = month < 10 ? "0" + month : "" + month;
        String ym = year + "-" + m;
        List<Performance> performanceList = performanceMapper.getPerformanceListByYM(ym);
        HashMap<Integer, BigDecimal> performanceMap = new HashMap<>(performanceList.size());
        for (Performance performance : performanceList) {
            Integer staffId = performance.getStaffId();
            if (performanceMap.containsKey(staffId)) {
                BigDecimal oldValue = performanceMap.get(staffId);
                performanceMap.put(staffId, oldValue.add(performance.getBonus()));
            } else {
                performanceMap.put(staffId, performance.getBonus());
            }
        }

        /* 5.依照前面的信息构造出每个员工的工资单 */
        for (Staff staff : staffList) {
            Payroll payroll = new Payroll();
            payroll.setDate(System.currentTimeMillis());
            payroll.setBasicWage(positionMap.get(staff.getPositionId()).add(qualificationMap.get(staff.getQualificationId())));
            payroll.setBonus(performanceMap.get(staff.getId()));
            payroll.setStaffId(staff.getId());
            BigDecimal total;
            if (payroll.getBonus() == null) {
                total = payroll.getBasicWage();
            } else {
                total = payroll.getBasicWage().add(payroll.getBonus());
            }
            payroll.setTotal(total);

            payrollMapper.addPayroll(payroll);
        }
    }
}
