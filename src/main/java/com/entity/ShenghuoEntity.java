package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 生活照料
 *
 * @author 
 * @email
 */
@TableName("shenghuo")
public class ShenghuoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ShenghuoEntity() {

	}

	public ShenghuoEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 老人
     */
    @ColumnInfo(comment="老人",type="int(11)")
    @TableField(value = "laoren_id")

    private Integer laorenId;


    /**
     * 生活照料名称
     */
    @ColumnInfo(comment="生活照料名称",type="varchar(200)")
    @TableField(value = "shenghuo_name")

    private String shenghuoName;


    /**
     * 生活照料编号
     */
    @ColumnInfo(comment="生活照料编号",type="varchar(200)")
    @TableField(value = "shenghuo_uuid_number")

    private String shenghuoUuidNumber;


    /**
     * 生活照料照片
     */
    @ColumnInfo(comment="生活照料照片",type="varchar(200)")
    @TableField(value = "shenghuo_photo")

    private String shenghuoPhoto;


    /**
     * 生活照料地点
     */
    @ColumnInfo(comment="生活照料地点",type="varchar(200)")
    @TableField(value = "shenghuo_address")

    private String shenghuoAddress;


    /**
     * 生活照料类型
     */
    @ColumnInfo(comment="生活照料类型",type="int(11)")
    @TableField(value = "shenghuo_types")

    private Integer shenghuoTypes;


    /**
     * 生活照料介绍
     */
    @ColumnInfo(comment="生活照料介绍",type="longtext")
    @TableField(value = "shenghuo_content")

    private String shenghuoContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "shenghuo_delete")

    private Integer shenghuoDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：生活照料名称
	 */
    public String getShenghuoName() {
        return shenghuoName;
    }
    /**
	 * 设置：生活照料名称
	 */

    public void setShenghuoName(String shenghuoName) {
        this.shenghuoName = shenghuoName;
    }
    /**
	 * 获取：生活照料编号
	 */
    public String getShenghuoUuidNumber() {
        return shenghuoUuidNumber;
    }
    /**
	 * 设置：生活照料编号
	 */

    public void setShenghuoUuidNumber(String shenghuoUuidNumber) {
        this.shenghuoUuidNumber = shenghuoUuidNumber;
    }
    /**
	 * 获取：生活照料照片
	 */
    public String getShenghuoPhoto() {
        return shenghuoPhoto;
    }
    /**
	 * 设置：生活照料照片
	 */

    public void setShenghuoPhoto(String shenghuoPhoto) {
        this.shenghuoPhoto = shenghuoPhoto;
    }
    /**
	 * 获取：生活照料地点
	 */
    public String getShenghuoAddress() {
        return shenghuoAddress;
    }
    /**
	 * 设置：生活照料地点
	 */

    public void setShenghuoAddress(String shenghuoAddress) {
        this.shenghuoAddress = shenghuoAddress;
    }
    /**
	 * 获取：生活照料类型
	 */
    public Integer getShenghuoTypes() {
        return shenghuoTypes;
    }
    /**
	 * 设置：生活照料类型
	 */

    public void setShenghuoTypes(Integer shenghuoTypes) {
        this.shenghuoTypes = shenghuoTypes;
    }
    /**
	 * 获取：生活照料介绍
	 */
    public String getShenghuoContent() {
        return shenghuoContent;
    }
    /**
	 * 设置：生活照料介绍
	 */

    public void setShenghuoContent(String shenghuoContent) {
        this.shenghuoContent = shenghuoContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getShenghuoDelete() {
        return shenghuoDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setShenghuoDelete(Integer shenghuoDelete) {
        this.shenghuoDelete = shenghuoDelete;
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

    @Override
    public String toString() {
        return "Shenghuo{" +
            ", id=" + id +
            ", laorenId=" + laorenId +
            ", shenghuoName=" + shenghuoName +
            ", shenghuoUuidNumber=" + shenghuoUuidNumber +
            ", shenghuoPhoto=" + shenghuoPhoto +
            ", shenghuoAddress=" + shenghuoAddress +
            ", shenghuoTypes=" + shenghuoTypes +
            ", shenghuoContent=" + shenghuoContent +
            ", shenghuoDelete=" + shenghuoDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
