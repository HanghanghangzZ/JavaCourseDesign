package com.hang.toilethrmanagement;

import com.hang.toilethrmanagement.mapper.*;
import com.hang.toilethrmanagement.model.*;
import com.hang.toilethrmanagement.service.AttendanceService;
import com.hang.toilethrmanagement.service.EmployeeInterviewService;
import com.hang.toilethrmanagement.service.TrainingService;
import com.hang.toilethrmanagement.utils.TimestampDiff;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class ToiletHrManagementApplicationTests {

    private StaffMapper staffMapper;

    @Autowired
    public void setStaffMapper(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }

    private LeaveRecordMapper leaveRecordMapper;

    @Autowired
    public void setLeaveRecordMapper(LeaveRecordMapper leaveRecordMapper) {
        this.leaveRecordMapper = leaveRecordMapper;
    }

    private PerformanceMapper performanceMapper;

    @Autowired
    public void setPerformanceMapper(PerformanceMapper performanceMapper) {
        this.performanceMapper = performanceMapper;
    }

    private AttendanceService attendanceService;

    @Autowired
    public void setAttendanceService(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    private EmployeeInterviewService employeeInterviewService;

    @Autowired
    public void setEmployeeInterviewService(EmployeeInterviewService employeeInterviewService) {
        this.employeeInterviewService = employeeInterviewService;
    }

    private TrainingService trainingService;

    @Autowired
    public void setTrainingService(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    /* ----------------------------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------------------------- */

    @Test
    void contextLoads() throws ParseException {
        for (int i = 0; i < 1000; i++) {
            Staff staff = new Staff();
            staff.setName("李" + i);
            staff.setIdCard("x");
            staff.setPositionId(4);
            staff.setAge(20);
            staff.setSex("女");
            staff.setQualificationId(4);
            staff.setEducation("初中");
            staff.setEntryTime(System.currentTimeMillis());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = simpleDateFormat.parse("2025-5-30 00:00:00");
            long ts = date.getTime();
            staff.setContractExpirationTime(ts);
            staff.setIsResigned(false);

            staffMapper.insertStaff(staff);
        }
    }

    @Test
    void leaveRecord() throws ParseException {
        for (int i = 0; i < 1000; i++) {
            LeaveRecord leaveRecord = new LeaveRecord();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = simpleDateFormat.parse("2021-6-1 8:00:00");
            long ts1 = date1.getTime();

            leaveRecord.setLeaveStartTime(ts1);

            Date date2 = simpleDateFormat.parse("2021-6-1 17:00:00");
            long ts2 = date2.getTime();

            leaveRecord.setLeaveEndTime(ts2);

            long diff = ts2 - ts1;//这样得到的差值是微秒级别
            long days = diff / (1000 * 60 * 60 * 24);

            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            leaveRecord.setLastTime(days + "天" + hours + "小时" + minutes + "分");
            leaveRecord.setLeaveReason("老板太黑心了，干个屁/(ㄒoㄒ)/~~");
            if (i < 300) {
                leaveRecord.setPass("未审批");
            } else if (i < 600) {
                leaveRecord.setPass("通过");
            } else {
                leaveRecord.setPass("不通过");
            }
            leaveRecord.setApprocverId(2);
            leaveRecord.setStaffId(i + 3);

            leaveRecordMapper.addLeaveRecord(leaveRecord);
        }
    }

    @Test
    public void test() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = df.parse("2021-5-31 8:00:00");
        Date d2 = df.parse("2021-5-31 17:00:00");
        Date d3 = df.parse("2021-5-30 17:00:00");
        System.out.println(d3.getTime());
        long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
        long days = diff / (1000 * 60 * 60 * 24);

        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
        System.out.println(days + "天" + hours + "小时" + minutes + "分");
    }

    @Test
    public void performance() {
        for (int i = 0; i < 1000; i++) {
            Performance performance = new Performance();

            performance.setStaffId(i + 2);
            performance.setDate(1622281144145L);
            performance.setBonus(new BigDecimal(1000));

            performanceMapper.addPerformance(performance);
        }
    }

    @Test
    public void attendance() throws ParseException {
        for (int i = 2; i <= 2004; i++) {
            Attendance attendance = new Attendance();
            attendance.setStaffId(i);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d1 = df.parse("2021-6-4 8:00:00");
            Date d2 = df.parse("2021-6-4 17:00:00");

            attendance.setArriveTime(d1.getTime());
            attendance.setLeaveTime(d2.getTime());
            String diff = TimestampDiff.diffToTimeStamp(d2.getTime() - d1.getTime());
            attendance.setLastTime(diff);

            attendanceService.addAttendance(attendance);
        }
    }

    @Test
    public void employeeInterview() {
        for (int i = 0; i < 1000; i++) {
            EmployeeInterview employeeInterview = new EmployeeInterview();
            employeeInterview.setName("王" + i);
            employeeInterview.setTelephone("x");

            if (i < 250) {
                employeeInterview.setInterviewStatus("0");
            } else if (i < 500) {
                employeeInterview.setInterviewStatus("1");
                employeeInterview.setInterviewSituation("你觉得他造马桶怎么样");
            } else if (i < 750) {
                employeeInterview.setInterviewStatus("2");
                employeeInterview.setInterviewSituation("这个人造马桶不错");
            } else {
                employeeInterview.setInterviewStatus("3");
                employeeInterview.setInterviewSituation("这个人造马桶不行");
            }
            employeeInterviewService.addEmployeeInterview(employeeInterview);
        }
    }

    @Test
    public void training() throws ParseException {
        for (int i = 2; i <= 2005; i++) {
            Training training = new Training();

            training.setTrainingMethod("入职培训");
            training.setScore(new BigDecimal(100));
            training.setStaffId(i);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d1 = df.parse("2021-5-30 8:00:00");
            Date d2 = df.parse("2021-5-31 17:00:00");

            training.setStartTime(d1.getTime());
            training.setEndTime(d2.getTime());

            String s = TimestampDiff.diffToTimeStamp(d2.getTime() - d1.getTime());
            training.setLastTime(s);

            trainingService.addTraining(training);
        }
    }

    @Test
    public void test2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date(Long.parseLong("1622281144145")));
        String format2 = sdf.format(new Date(1622281144145l));
        String format3 = sdf.format(new Date(1622281144145l / 1000));
        System.out.println(format);
        System.out.println(format2);
        System.out.println(format3);

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int dow = cal.get(Calendar.DAY_OF_WEEK);
        int dom = cal.get(Calendar.DAY_OF_MONTH);
        int doy = cal.get(Calendar.DAY_OF_YEAR);

        System.out.println("当期时间: " + cal.getTime());
        System.out.println("日期: " + day);
        System.out.println("月份: " + month);
        System.out.println("年份: " + year);
        System.out.println("一周的第几天: " + dow);  // 星期日为一周的第一天输出为 1，星期一输出为 2，以此类推
        System.out.println("一月中的第几天: " + dom);
        System.out.println("一年的第几天: " + doy);
    }
}