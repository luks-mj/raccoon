package com.mujun.mng.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 国税数据模型
 */
@TableName("db_country_taxation")
public class CountryTaxationModel implements Serializable {

    @TableId(value = "id")
    private String id;

    /**
     * 企业名称
     */
    @TableField(value = "enterPrise_name")
    private String enterPriseName;

    /**
     * 企业统一认证码
     */
    @TableField(value = "enterPrise_code")
    private String enterPriseCode;

    /**
     * 国税缴纳金额
     */
    @TableField(value = "taxation_data")
    private Long taxationData;

    /**
     * 国税费用明细
     */
    @TableField(value = "taxation_detail")
    private String taxationDetail;

    /**
     * 导入用户
     */
    @TableField(value = "user_id")
    private Long userId;


    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")//数据库导出页面时json格式化
    @TableField(value = "create_date")
    private Date createDate;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")//数据库导出页面时json格式化
    @TableField(value = "update_date")
    private Date updateDate;

    /**
     * 备注信息
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnterPriseName() {
        return enterPriseName;
    }

    public void setEnterPriseName(String enterPriseName) {
        this.enterPriseName = enterPriseName;
    }

    public String getEnterPriseCode() {
        return enterPriseCode;
    }

    public void setEnterPriseCode(String enterPriseCode) {
        this.enterPriseCode = enterPriseCode;
    }

    public Long getTaxationData() {
        return taxationData;
    }

    public void setTaxationData(Long taxationData) {
        this.taxationData = taxationData;
    }

    public String getTaxationDetail() {
        return taxationDetail;
    }

    public void setTaxationDetail(String taxationDetail) {
        this.taxationDetail = taxationDetail;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
