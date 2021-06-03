package com.hang.toilethrmanagement.utils;

import com.hang.toilethrmanagement.bean.Pagination;

public class PaginationUtil {

    public static Pagination getPagination(Integer totalCount, Integer pageSize, Integer pageNum) {
        Integer totalPage;
        if (totalCount % pageSize == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }
        if (pageNum <= 0) {
            pageNum = 1;
        } else if (pageNum > totalPage && totalPage != 0) {
            pageNum = totalPage;
        }

        int offset = pageSize * (pageNum - 1);

        Pagination pagination = new Pagination();
        pagination.setOffset(offset);
        pagination.setPageSize(pageSize);
        pagination.setTotalPage(totalPage);
        pagination.setTotalCount(totalCount);
        pagination.setPageNum(pageNum);

        return pagination;
    }
}
