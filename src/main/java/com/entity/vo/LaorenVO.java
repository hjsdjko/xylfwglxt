package com.entity.vo;

import com.entity.LaorenEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 老人
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("laoren")
public class LaorenVO implements Serializable {
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
     * 老人名称
     */

    @TableField(value = "laoren_name")
    private String laorenName;


    /**
     * 联系方式
     */

    @TableField(value = "laoren_phone")
    private String laorenPhone;


    /**
     * 邮箱
     */

    @TableField(value = "laoren_email")
    private String laorenEmail;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 余额
     */

    @TableField(value = "new_money")
    private Double newMoney;


    /**
     * 老人介绍
     */

    @TableField(value = "laoren_content")
    private String laorenContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "laoren_delete")
    private Integer laorenDelete;


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
	 * 设置：老人名称
	 */
    public String getLaorenName() {
        return laorenName;
    }


    /**
	 * 获取：老人名称
	 */

    public void setLaorenName(String laorenName) {
        this.laorenName = laorenName;
    }
    /**
	 * 设置：联系方式
	 */
    public String getLaorenPhone() {
        return laorenPhone;
    }


    /**
	 * 获取：联系方式
	 */

    public void setLaorenPhone(String laorenPhone) {
        this.laorenPhone = laorenPhone;
    }
    /**
	 * 设置：邮箱
	 */
    public String getLaorenEmail() {
        return laorenEmail;
    }


    /**
	 * 获取：邮箱
	 */

    public void setLaorenEmail(String laorenEmail) {
        this.laorenEmail = laorenEmail;
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
	 * 设置：余额
	 */
    public Double getNewMoney() {
        return newMoney;
    }


    /**
	 * 获取：余额
	 */

    public void setNewMoney(Double newMoney) {
        this.newMoney = newMoney;
    }
    /**
	 * 设置：老人介绍
	 */
    public String getLaorenContent() {
        return laorenContent;
    }


    /**
	 * 获取：老人介绍
	 */

    public void setLaorenContent(String laorenContent) {
        this.laorenContent = laorenContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getLaorenDelete() {
        return laorenDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setLaorenDelete(Integer laorenDelete) {
        this.laorenDelete = laorenDelete;
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
