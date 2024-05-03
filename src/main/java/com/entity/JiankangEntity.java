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
 * 健康档案
 *
 * @author 
 * @email
 */
@TableName("jiankang")
public class JiankangEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JiankangEntity() {

	}

	public JiankangEntity(T t) {
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
     * 健康档案名称
     */
    @ColumnInfo(comment="健康档案名称",type="varchar(200)")
    @TableField(value = "jiankang_name")

    private String jiankangName;


    /**
     * 健康档案编号
     */
    @ColumnInfo(comment="健康档案编号",type="varchar(200)")
    @TableField(value = "jiankang_uuid_number")

    private String jiankangUuidNumber;


    /**
     * 健康档案照片
     */
    @ColumnInfo(comment="健康档案照片",type="varchar(200)")
    @TableField(value = "jiankang_photo")

    private String jiankangPhoto;


    /**
     * 健康档案地点
     */
    @ColumnInfo(comment="健康档案地点",type="varchar(200)")
    @TableField(value = "jiankang_address")

    private String jiankangAddress;


    /**
     * 身体情况
     */
    @ColumnInfo(comment="身体情况",type="int(11)")
    @TableField(value = "jiankang_types")

    private Integer jiankangTypes;


    /**
     * 注意事项
     */
    @ColumnInfo(comment="注意事项",type="longtext")
    @TableField(value = "jiankang_content")

    private String jiankangContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "jiankang_delete")

    private Integer jiankangDelete;


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

    @Override
    public String toString() {
        return "Jiankang{" +
            ", id=" + id +
            ", yiliaoId=" + yiliaoId +
            ", laorenId=" + laorenId +
            ", jiankangName=" + jiankangName +
            ", jiankangUuidNumber=" + jiankangUuidNumber +
            ", jiankangPhoto=" + jiankangPhoto +
            ", jiankangAddress=" + jiankangAddress +
            ", jiankangTypes=" + jiankangTypes +
            ", jiankangContent=" + jiankangContent +
            ", jiankangDelete=" + jiankangDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
