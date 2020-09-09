package com.mujun.mng.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 国土数据模型
 */
@TableName("db_country_land")
public class CountryLandModel implements Serializable {

    private Long id;

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
     * 登记用地面积
     */
    @TableField(value = "register_area")
    private Long registerArea;

    /**
     * 承租用地面积
     */
    @TableField(value = "lessee_area")
    private Long lesseeArea;

    /**
     * 出租用地面积
     */
    @TableField(value = "lease_area")
    private Long leaseArea;

    /**
     * 导入用户
     */
    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "create_date")
    private Date createDate;

    @TableField(value = "update_date")
    private Date updateDate;

    /**
     * 备注信息
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getRegisterArea() {
        return registerArea;
    }

    public void setRegisterArea(Long registerArea) {
        this.registerArea = registerArea;
    }

    public Long getLesseeArea() {
        return lesseeArea;
    }

    public void setLesseeArea(Long lesseeArea) {
        this.lesseeArea = lesseeArea;
    }

    public Long getLeaseArea() {
        return leaseArea;
    }

    public void setLeaseArea(Long leaseArea) {
        this.leaseArea = leaseArea;
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

    @Override
    public String toString() {
        return "ContryLandModel{" +
                "id=" + id +
                ", enterPriseName='" + enterPriseName + '\'' +
                ", enterPriseCode='" + enterPriseCode + '\'' +
                ", registerArea=" + registerArea +
                ", lesseeArea=" + lesseeArea +
                ", leaseArea=" + leaseArea +
                ", userId=" + userId +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
