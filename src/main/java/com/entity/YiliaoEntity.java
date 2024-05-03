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
 * 医疗人员
 *
 * @author 
 * @email
 */
@TableName("yiliao")
public class YiliaoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YiliaoEntity() {

	}

	public YiliaoEntity(T t) {
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
     * 账户
     */
    @ColumnInfo(comment="账户",type="varchar(200)")
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @ColumnInfo(comment="密码",type="varchar(200)")
    @TableField(value = "password")

    private String password;


    /**
     * 医疗人员名称
     */
    @ColumnInfo(comment="医疗人员名称",type="varchar(200)")
    @TableField(value = "yiliao_name")

    private String yiliaoName;


    /**
     * 联系方式
     */
    @ColumnInfo(comment="联系方式",type="varchar(200)")
    @TableField(value = "yiliao_phone")

    private String yiliaoPhone;


    /**
     * 邮箱
     */
    @ColumnInfo(comment="邮箱",type="varchar(200)")
    @TableField(value = "yiliao_email")

    private String yiliaoEmail;


    /**
     * 性别
     */
    @ColumnInfo(comment="性别",type="int(11)")
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 医疗人员介绍
     */
    @ColumnInfo(comment="医疗人员介绍",type="longtext")
    @TableField(value = "yiliao_content")

    private String yiliaoContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "yiliao_delete")

    private Integer yiliaoDelete;


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
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }
    /**
	 * 设置：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }
    /**
	 * 设置：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：医疗人员名称
	 */
    public String getYiliaoName() {
        return yiliaoName;
    }
    /**
	 * 设置：医疗人员名称
	 */

    public void setYiliaoName(String yiliaoName) {
        this.yiliaoName = yiliaoName;
    }
    /**
	 * 获取：联系方式
	 */
    public String getYiliaoPhone() {
        return yiliaoPhone;
    }
    /**
	 * 设置：联系方式
	 */

    public void setYiliaoPhone(String yiliaoPhone) {
        this.yiliaoPhone = yiliaoPhone;
    }
    /**
	 * 获取：邮箱
	 */
    public String getYiliaoEmail() {
        return yiliaoEmail;
    }
    /**
	 * 设置：邮箱
	 */

    public void setYiliaoEmail(String yiliaoEmail) {
        this.yiliaoEmail = yiliaoEmail;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }
    /**
	 * 设置：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：医疗人员介绍
	 */
    public String getYiliaoContent() {
        return yiliaoContent;
    }
    /**
	 * 设置：医疗人员介绍
	 */

    public void setYiliaoContent(String yiliaoContent) {
        this.yiliaoContent = yiliaoContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getYiliaoDelete() {
        return yiliaoDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setYiliaoDelete(Integer yiliaoDelete) {
        this.yiliaoDelete = yiliaoDelete;
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
        return "Yiliao{" +
            ", id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", yiliaoName=" + yiliaoName +
            ", yiliaoPhone=" + yiliaoPhone +
            ", yiliaoEmail=" + yiliaoEmail +
            ", sexTypes=" + sexTypes +
            ", yiliaoContent=" + yiliaoContent +
            ", yiliaoDelete=" + yiliaoDelete +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
