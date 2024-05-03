package com.entity.model;

import com.entity.YiliaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 医疗人员
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YiliaoModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 医疗人员名称
     */
    private String yiliaoName;


    /**
     * 联系方式
     */
    private String yiliaoPhone;


    /**
     * 邮箱
     */
    private String yiliaoEmail;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 医疗人员介绍
     */
    private String yiliaoContent;


    /**
     * 逻辑删除
     */
    private Integer yiliaoDelete;


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

    }
