package com.entity.model;

import com.entity.LaorenEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 老人
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class LaorenModel implements Serializable {
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
     * 老人名称
     */
    private String laorenName;


    /**
     * 联系方式
     */
    private String laorenPhone;


    /**
     * 邮箱
     */
    private String laorenEmail;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 余额
     */
    private Double newMoney;


    /**
     * 老人介绍
     */
    private String laorenContent;


    /**
     * 逻辑删除
     */
    private Integer laorenDelete;


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
	 * 获取：老人名称
	 */
    public String getLaorenName() {
        return laorenName;
    }


    /**
	 * 设置：老人名称
	 */
    public void setLaorenName(String laorenName) {
        this.laorenName = laorenName;
    }
    /**
	 * 获取：联系方式
	 */
    public String getLaorenPhone() {
        return laorenPhone;
    }


    /**
	 * 设置：联系方式
	 */
    public void setLaorenPhone(String laorenPhone) {
        this.laorenPhone = laorenPhone;
    }
    /**
	 * 获取：邮箱
	 */
    public String getLaorenEmail() {
        return laorenEmail;
    }


    /**
	 * 设置：邮箱
	 */
    public void setLaorenEmail(String laorenEmail) {
        this.laorenEmail = laorenEmail;
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
	 * 获取：余额
	 */
    public Double getNewMoney() {
        return newMoney;
    }


    /**
	 * 设置：余额
	 */
    public void setNewMoney(Double newMoney) {
        this.newMoney = newMoney;
    }
    /**
	 * 获取：老人介绍
	 */
    public String getLaorenContent() {
        return laorenContent;
    }


    /**
	 * 设置：老人介绍
	 */
    public void setLaorenContent(String laorenContent) {
        this.laorenContent = laorenContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getLaorenDelete() {
        return laorenDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setLaorenDelete(Integer laorenDelete) {
        this.laorenDelete = laorenDelete;
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
