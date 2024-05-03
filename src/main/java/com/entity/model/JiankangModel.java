package com.entity.model;

import com.entity.JiankangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 健康档案
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JiankangModel implements Serializable {
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
     * 健康档案名称
     */
    private String jiankangName;


    /**
     * 健康档案编号
     */
    private String jiankangUuidNumber;


    /**
     * 健康档案照片
     */
    private String jiankangPhoto;


    /**
     * 健康档案地点
     */
    private String jiankangAddress;


    /**
     * 身体情况
     */
    private Integer jiankangTypes;


    /**
     * 注意事项
     */
    private String jiankangContent;


    /**
     * 逻辑删除
     */
    private Integer jiankangDelete;


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
	 * 获取：健康档案名称
	 */
    public String getJiankangName() {
        return jiankangName;
    }


    /**
	 * 设置：健康档案名称
	 */
    public void setJiankangName(String jiankangName) {
        this.jiankangName = jiankangName;
    }
    /**
	 * 获取：健康档案编号
	 */
    public String getJiankangUuidNumber() {
        return jiankangUuidNumber;
    }


    /**
	 * 设置：健康档案编号
	 */
    public void setJiankangUuidNumber(String jiankangUuidNumber) {
        this.jiankangUuidNumber = jiankangUuidNumber;
    }
    /**
	 * 获取：健康档案照片
	 */
    public String getJiankangPhoto() {
        return jiankangPhoto;
    }


    /**
	 * 设置：健康档案照片
	 */
    public void setJiankangPhoto(String jiankangPhoto) {
        this.jiankangPhoto = jiankangPhoto;
    }
    /**
	 * 获取：健康档案地点
	 */
    public String getJiankangAddress() {
        return jiankangAddress;
    }


    /**
	 * 设置：健康档案地点
	 */
    public void setJiankangAddress(String jiankangAddress) {
        this.jiankangAddress = jiankangAddress;
    }
    /**
	 * 获取：身体情况
	 */
    public Integer getJiankangTypes() {
        return jiankangTypes;
    }


    /**
	 * 设置：身体情况
	 */
    public void setJiankangTypes(Integer jiankangTypes) {
        this.jiankangTypes = jiankangTypes;
    }
    /**
	 * 获取：注意事项
	 */
    public String getJiankangContent() {
        return jiankangContent;
    }


    /**
	 * 设置：注意事项
	 */
    public void setJiankangContent(String jiankangContent) {
        this.jiankangContent = jiankangContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getJiankangDelete() {
        return jiankangDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setJiankangDelete(Integer jiankangDelete) {
        this.jiankangDelete = jiankangDelete;
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
