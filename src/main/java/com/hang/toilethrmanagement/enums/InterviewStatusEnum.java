package com.hang.toilethrmanagement.enums;

import com.hang.toilethrmanagement.model.EmployeeInterview;

public enum InterviewStatusEnum {
    WAIT_INTERVIEW(0, "未面试"),
    IS_INTERVIEW(1, "已面试"),
    PASS_INTERVIEW(2, "通过"),
    FAIL_INTERVIEW(3, "面试未通过");
    private Integer index;
    private String interviewStatus;

    public static boolean containStatus(String interviewStatus) {
        return "0".equals(interviewStatus) ||
                "1".equals(interviewStatus) ||
                "2".equals(interviewStatus) ||
                "3".equals(interviewStatus);
    }

    public Integer getIndex() {
        return index;
    }

    public String getInterviewStatus() {
        return interviewStatus;
    }

    InterviewStatusEnum(Integer index, String interviewStatus) {
        this.index = index;
        this.interviewStatus = interviewStatus;
    }

    public static String indexToStatus(String interviewStatus) {
        if ("0".equals(interviewStatus)) {
            return WAIT_INTERVIEW.getInterviewStatus();
        } else if ("1".equals(interviewStatus)) {
            return IS_INTERVIEW.getInterviewStatus();
        } else if ("2".equals(interviewStatus)) {
            return PASS_INTERVIEW.getInterviewStatus();
        } else {
            return FAIL_INTERVIEW.getInterviewStatus();
        }
    }

    public static boolean isPass(EmployeeInterview employeeInterview) {

        return "通过".equals(employeeInterview.getInterviewStatus()) || "2".equals(employeeInterview.getInterviewStatus());
    }

    public static boolean isApprove(EmployeeInterview employeeInterview) {

        return "已面试".equals(employeeInterview.getInterviewStatus()) || "1".equals(employeeInterview.getInterviewStatus());
    }

    public static boolean isFail(EmployeeInterview employeeInterview) {

        return "面试未通过".equals(employeeInterview.getInterviewStatus()) || "3".equals(employeeInterview.getInterviewStatus());
    }

    public static boolean isNotInterviewed(EmployeeInterview employeeInterview) {

        return "未面试".equals(employeeInterview.getInterviewStatus()) || "0".equals(employeeInterview.getInterviewStatus());
    }
}
