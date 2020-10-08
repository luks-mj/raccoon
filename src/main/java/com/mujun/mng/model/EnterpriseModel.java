package com.mujun.mng.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业数据模型
 */
@TableName("db_enterprise")
public class EnterpriseModel implements Serializable {


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
     * 企业规模
     */
    @TableField(value = "enterprise_scale")
    private String enterpriseScale;

    /**
     * 行业类别
     */
    @TableField(value = "industry_type")
    private String industryType;

    /**
     * 状态
     */
    @TableField(value = "status")
    private String status;
    /**
     * 企业类别
     */
    @TableField(value = "enterprise_type")
    private String enterpriseType;

    /**
     * 所属网格
     */
    @TableField(value = "belong_network")
    private String belongNetwork;

    /**
     * 详细地址
     */
    @TableField(value = "detail_address")
    private String detailAddress;

    /**
     * 联系人
     */
    @TableField(value = "contact")
    private String contact;

    /**
     * 联系电话
     */
    @TableField(value = "contact_num")
    private String contactNum;
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

    @TableField(value = "year")
    private Integer year;

    @TableField(value = "month")
    private  Integer month;
    /**
     * 备注信息
     */
    @TableField(value = "remark")
    private String remark;


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

    public String getEnterpriseScale() {
        return enterpriseScale;
    }

    public void setEnterpriseScale(String enterpriseScale) {
        this.enterpriseScale = enterpriseScale;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(String enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public String getBelongNetwork() {
        return belongNetwork;
    }

    public void setBelongNetwork(String belongNetwork) {
        this.belongNetwork = belongNetwork;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
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
        return "EnterpriseModel{" +
                "id='" + id + '\'' +
                ", enterPriseName='" + enterPriseName + '\'' +
                ", enterPriseCode='" + enterPriseCode + '\'' +
                ", enterpriseScale='" + enterpriseScale + '\'' +
                ", industryType='" + industryType + '\'' +
                ", status='" + status + '\'' +
                ", enterpriseType='" + enterpriseType + '\'' +
                ", belongNetwork='" + belongNetwork + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", contact='" + contact + '\'' +
                ", contactNum='" + contactNum + '\'' +
                ", userId=" + userId +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", year=" + year +
                ", month=" + month +
                ", remark='" + remark + '\'' +
                '}';
    }
}
