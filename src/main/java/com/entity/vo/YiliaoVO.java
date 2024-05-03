package com.entity.vo;

import com.entity.YiliaoEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 医疗人员
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("yiliao")
public class YiliaoVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 医疗人员名称
     */

    @TableField(value = "yiliao_name")
    private String yiliaoName;


    /**
     * 联系方式
     */

    @TableField(value = "yiliao_phone")
    private String yiliaoPhone;


    /**
     * 邮箱
     */

    @TableField(value = "yiliao_email")
    private String yiliaoEmail;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 医疗人员介绍
     */

    @TableField(value = "yiliao_content")
    private String yiliaoContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "yiliao_delete")
    private Integer yiliaoDelete;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：医疗人员名称
	 */
    public String getYiliaoName() {
        return yiliaoName;
    }


    /**
	 * 获取：医疗人员名称
	 */

    public void setYiliaoName(String yiliaoName) {
        this.yiliaoName = yiliaoName;
    }
    /**
	 * 设置：联系方式
	 */
    public String getYiliaoPhone() {
        return yiliaoPhone;
    }


    /**
	 * 获取：联系方式
	 */

    public void setYiliaoPhone(String yiliaoPhone) {
        this.yiliaoPhone = yiliaoPhone;
    }
    /**
	 * 设置：邮箱
	 */
    public String getYiliaoEmail() {
        return yiliaoEmail;
    }


    /**
	 * 获取：邮箱
	 */

    public void setYiliaoEmail(String yiliaoEmail) {
        this.yiliaoEmail = yiliaoEmail;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：医疗人员介绍
	 */
    public String getYiliaoContent() {
        return yiliaoContent;
    }


    /**
	 * 获取：医疗人员介绍
	 */

    public void setYiliaoContent(String yiliaoContent) {
        this.yiliaoContent = yiliaoContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getYiliaoDelete() {
        return yiliaoDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setYiliaoDelete(Integer yiliaoDelete) {
        this.yiliaoDelete = yiliaoDelete;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
