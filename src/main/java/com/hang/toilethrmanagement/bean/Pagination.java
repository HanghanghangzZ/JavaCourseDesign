package com.hang.toilethrmanagement.bean;

import lombok.Data;

@Data
public class Pagination {
    private Integer totalCount;
    private Integer totalPage;
    private Integer pageSize;
    private Integer pageNum;
    private Integer offset;
}
