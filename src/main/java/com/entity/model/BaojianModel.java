package com.entity.model;

import com.entity.BaojianEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 医疗保健
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class BaojianModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 医疗人员
     */
    private Integer yiliaoId;


    /**
     * 老人
     */
    private Integer laorenId;


    /**
     * 医疗保健名称
     */
    private String baojianName;


    /**
     * 医疗保健编号
     */
    private String baojianUuidNumber;


    /**
     * 医疗保健照片
     */
    private String baojianPhoto;


    /**
     * 医疗保健地点
     */
    private String baojianAddress;


    /**
     * 医疗保健类型
     */
    private Integer baojianTypes;


    /**
     * 注意事项
     */
    private String baojianContent;


    /**
     * 逻辑删除
     */
    private Integer baojianDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：医疗人员
	 */
    public Integer getYiliaoId() {
        return yiliaoId;
    }


    /**
	 * 设置：医疗人员
	 */
    public void setYiliaoId(Integer yiliaoId) {
        this.yiliaoId = yiliaoId;
    }
    /**
	 * 获取：老人
	 */
    public Integer getLaorenId() {
        return laorenId;
    }


    /**
	 * 设置：老人
	 */
    public void setLaorenId(Integer laorenId) {
        this.laorenId = laorenId;
    }
    /**
	 * 获取：医疗保健名称
	 */
    public String getBaojianName() {
        return baojianName;
    }


    /**
	 * 设置：医疗保健名称
	 */
    public void setBaojianName(String baojianName) {
        this.baojianName = baojianName;
    }
    /**
	 * 获取：医疗保健编号
	 */
    public String getBaojianUuidNumber() {
        return baojianUuidNumber;
    }


    /**
	 * 设置：医疗保健编号
	 */
    public void setBaojianUuidNumber(String baojianUuidNumber) {
        this.baojianUuidNumber = baojianUuidNumber;
    }
    /**
	 * 获取：医疗保健照片
	 */
    public String getBaojianPhoto() {
        return baojianPhoto;
    }


    /**
	 * 设置：医疗保健照片
	 */
    public void setBaojianPhoto(String baojianPhoto) {
        this.baojianPhoto = baojianPhoto;
    }
    /**
	 * 获取：医疗保健地点
	 */
    public String getBaojianAddress() {
        return baojianAddress;
    }


    /**
	 * 设置：医疗保健地点
	 */
    public void setBaojianAddress(String baojianAddress) {
        this.baojianAddress = baojianAddress;
    }
    /**
	 * 获取：医疗保健类型
	 */
    public Integer getBaojianTypes() {
        return baojianTypes;
    }


    /**
	 * 设置：医疗保健类型
	 */
    public void setBaojianTypes(Integer baojianTypes) {
        this.baojianTypes = baojianTypes;
    }
    /**
	 * 获取：注意事项
	 */
    public String getBaojianContent() {
        return baojianContent;
    }


    /**
	 * 设置：注意事项
	 */
    public void setBaojianContent(String baojianContent) {
        this.baojianContent = baojianContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getBaojianDelete() {
        return baojianDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setBaojianDelete(Integer baojianDelete) {
        this.baojianDelete = baojianDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：录入时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
