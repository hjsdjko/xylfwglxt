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
 * 土地
 *
 * @author 
 * @email
 */
@TableName("tudi")
public class TudiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public TudiEntity() {

	}

	public TudiEntity(T t) {
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
     * 商家
     */
    @ColumnInfo(comment="商家",type="int(11)")
    @TableField(value = "laoren_id")

    private Integer laorenId;


    /**
     * 土地名称
     */
    @ColumnInfo(comment="土地名称",type="varchar(200)")
    @TableField(value = "tudi_name")

    private String tudiName;


    /**
     * 土地编号
     */
    @ColumnInfo(comment="土地编号",type="varchar(200)")
    @TableField(value = "tudi_uuid_number")

    private String tudiUuidNumber;


    /**
     * 土地照片
     */
    @ColumnInfo(comment="土地照片",type="varchar(200)")
    @TableField(value = "tudi_photo")

    private String tudiPhoto;


    /**
     * 土地地点
     */
    @ColumnInfo(comment="土地地点",type="varchar(200)")
    @TableField(value = "tudi_address")

    private String tudiAddress;


    /**
     * 土地类型
     */
    @ColumnInfo(comment="土地类型",type="int(11)")
    @TableField(value = "tudi_types")

    private Integer tudiTypes;


    /**
     * 多少亩
     */
    @ColumnInfo(comment="多少亩",type="int(11)")
    @TableField(value = "tudi_kucun_number")

    private Integer tudiKucunNumber;


    /**
     * 价格/年
     */
    @ColumnInfo(comment="价格/年",type="decimal(10,2)")
    @TableField(value = "tudi_new_money")

    private Double tudiNewMoney;


    /**
     * 土地介绍
     */
    @ColumnInfo(comment="土地介绍",type="longtext")
    @TableField(value = "tudi_content")

    private String tudiContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "tudi_delete")

    private Integer tudiDelete;


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
	 * 获取：商家
	 */
    public Integer getLaorenId() {
        return laorenId;
    }
    /**
	 * 设置：商家
	 */

    public void setLaorenId(Integer laorenId) {
        this.laorenId = laorenId;
    }
    /**
	 * 获取：土地名称
	 */
    public String getTudiName() {
        return tudiName;
    }
    /**
	 * 设置：土地名称
	 */

    public void setTudiName(String tudiName) {
        this.tudiName = tudiName;
    }
    /**
	 * 获取：土地编号
	 */
    public String getTudiUuidNumber() {
        return tudiUuidNumber;
    }
    /**
	 * 设置：土地编号
	 */

    public void setTudiUuidNumber(String tudiUuidNumber) {
        this.tudiUuidNumber = tudiUuidNumber;
    }
    /**
	 * 获取：土地照片
	 */
    public String getTudiPhoto() {
        return tudiPhoto;
    }
    /**
	 * 设置：土地照片
	 */

    public void setTudiPhoto(String tudiPhoto) {
        this.tudiPhoto = tudiPhoto;
    }
    /**
	 * 获取：土地地点
	 */
    public String getTudiAddress() {
        return tudiAddress;
    }
    /**
	 * 设置：土地地点
	 */

    public void setTudiAddress(String tudiAddress) {
        this.tudiAddress = tudiAddress;
    }
    /**
	 * 获取：土地类型
	 */
    public Integer getTudiTypes() {
        return tudiTypes;
    }
    /**
	 * 设置：土地类型
	 */

    public void setTudiTypes(Integer tudiTypes) {
        this.tudiTypes = tudiTypes;
    }
    /**
	 * 获取：多少亩
	 */
    public Integer getTudiKucunNumber() {
        return tudiKucunNumber;
    }
    /**
	 * 设置：多少亩
	 */

    public void setTudiKucunNumber(Integer tudiKucunNumber) {
        this.tudiKucunNumber = tudiKucunNumber;
    }
    /**
	 * 获取：价格/年
	 */
    public Double getTudiNewMoney() {
        return tudiNewMoney;
    }
    /**
	 * 设置：价格/年
	 */

    public void setTudiNewMoney(Double tudiNewMoney) {
        this.tudiNewMoney = tudiNewMoney;
    }
    /**
	 * 获取：土地介绍
	 */
    public String getTudiContent() {
        return tudiContent;
    }
    /**
	 * 设置：土地介绍
	 */

    public void setTudiContent(String tudiContent) {
        this.tudiContent = tudiContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getTudiDelete() {
        return tudiDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setTudiDelete(Integer tudiDelete) {
        this.tudiDelete = tudiDelete;
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
        return "Tudi{" +
            ", id=" + id +
            ", laorenId=" + laorenId +
            ", tudiName=" + tudiName +
            ", tudiUuidNumber=" + tudiUuidNumber +
            ", tudiPhoto=" + tudiPhoto +
            ", tudiAddress=" + tudiAddress +
            ", tudiTypes=" + tudiTypes +
            ", tudiKucunNumber=" + tudiKucunNumber +
            ", tudiNewMoney=" + tudiNewMoney +
            ", tudiContent=" + tudiContent +
            ", tudiDelete=" + tudiDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
