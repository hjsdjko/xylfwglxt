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
 * 医疗保健
 *
 * @author 
 * @email
 */
@TableName("baojian")
public class BaojianEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public BaojianEntity() {

	}

	public BaojianEntity(T t) {
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
     * 医疗人员
     */
    @ColumnInfo(comment="医疗人员",type="int(11)")
    @TableField(value = "yiliao_id")

    private Integer yiliaoId;


    /**
     * 老人
     */
    @ColumnInfo(comment="老人",type="int(11)")
    @TableField(value = "laoren_id")

    private Integer laorenId;


    /**
     * 医疗保健名称
     */
    @ColumnInfo(comment="医疗保健名称",type="varchar(200)")
    @TableField(value = "baojian_name")

    private String baojianName;


    /**
     * 医疗保健编号
     */
    @ColumnInfo(comment="医疗保健编号",type="varchar(200)")
    @TableField(value = "baojian_uuid_number")

    private String baojianUuidNumber;


    /**
     * 医疗保健照片
     */
    @ColumnInfo(comment="医疗保健照片",type="varchar(200)")
    @TableField(value = "baojian_photo")

    private String baojianPhoto;


    /**
     * 医疗保健地点
     */
    @ColumnInfo(comment="医疗保健地点",type="varchar(200)")
    @TableField(value = "baojian_address")

    private String baojianAddress;


    /**
     * 医疗保健类型
     */
    @ColumnInfo(comment="医疗保健类型",type="int(11)")
    @TableField(value = "baojian_types")

    private Integer baojianTypes;


    /**
     * 注意事项
     */
    @ColumnInfo(comment="注意事项",type="longtext")
    @TableField(value = "baojian_content")

    private String baojianContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "baojian_delete")

    private Integer baojianDelete;


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

    @Override
    public String toString() {
        return "Baojian{" +
            ", id=" + id +
            ", yiliaoId=" + yiliaoId +
            ", laorenId=" + laorenId +
            ", baojianName=" + baojianName +
            ", baojianUuidNumber=" + baojianUuidNumber +
            ", baojianPhoto=" + baojianPhoto +
            ", baojianAddress=" + baojianAddress +
            ", baojianTypes=" + baojianTypes +
            ", baojianContent=" + baojianContent +
            ", baojianDelete=" + baojianDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
