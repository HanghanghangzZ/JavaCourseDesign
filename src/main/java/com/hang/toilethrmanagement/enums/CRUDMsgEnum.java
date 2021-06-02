package com.hang.toilethrmanagement.enums;

public enum CRUDMsgEnum {
    SELECT_SUCCESS("查询成功"),
    UPDATE_SUCCESS("更新成功"),
    DELETE_SUCCESS("删除成功"),
    INSERT_SUCCESS("增加成功"),
    SELECT_FAIL("查询失败"),
    UPDATE_FAIL("更新失败"),
    DELETE_FAIL("删除失败"),
    INSERT_FAIL("增加失败");
    private String msg;

    CRUDMsgEnum(String msg) {
        this.msg = msg;
    }
}
