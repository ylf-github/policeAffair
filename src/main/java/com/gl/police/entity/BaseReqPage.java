package com.gl.police.entity;

/**
 * @author: leifeng.ye
 * @date: 2020-01-11
 * @desc:
 */
public class BaseReqPage {

    private Integer pageSize=10;

    private Integer pageNo=0;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
