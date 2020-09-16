package com.mujun.mng.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业统计数据导入
 */
@TableName("db_count_enterprise")
public class EnterpriseCountModel implements Serializable {

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
     * 工业产值（万元）
     */
    @TableField("industry_pro")
    private Double industryPro;

    /**
     * 工业增加值（万元）
     */
    @TableField(value = "industry_put")
    private  Double industryPut;

    /**
     * R&D经费占主营业收入比重
     */
    @TableField(value = "industry_ratio")
    private  Double industryRatio;


    /**
     * 年平均职工数
     */
    @TableField(value = "industry_avg_person")
    private Integer industryAvgPerson;


    /**
     * 综合能耗
     */
    @TableField(value = "energy_consumption")
    private  Double energyConsumption;

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
    @TableField(value = "remark")
    private String remark;

    @TableField(value = "year")
    private Integer year;

    @TableField(value = "month")
    private  Integer month;


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

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

    public Double getIndustryPro() {
        return industryPro;
    }

    public void setIndustryPro(Double industryPro) {
        this.industryPro = industryPro;
    }

    public Double getIndustryPut() {
        return industryPut;
    }

    public void setIndustryPut(Double industryPut) {
        this.industryPut = industryPut;
    }

    public Double getIndustryRatio() {
        return industryRatio;
    }

    public void setIndustryRatio(Double industryRatio) {
        this.industryRatio = industryRatio;
    }

    public Integer getIndustryAvgPerson() {
        return industryAvgPerson;
    }

    public void setIndustryAvgPerson(Integer industryAvgPerson) {
        this.industryAvgPerson = industryAvgPerson;
    }

    public Double getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(Double energyConsumption) {
        this.energyConsumption = energyConsumption;
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
