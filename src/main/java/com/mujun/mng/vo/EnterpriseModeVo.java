package com.mujun.mng.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

public class EnterpriseModeVo implements Serializable {

    private Integer pageNo;
    private Integer pageSize;
    private String enterpriseName;
    private String enterpriseCode;

    @DateTimeFormat(pattern="yyyy-MM-dd")//页面传入数据格式化
    private String createDate;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
