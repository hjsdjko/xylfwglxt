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
 * 生活照料订单
 *
 * @author 
 * @email
 */
@TableName("shenghuo_order")
public class ShenghuoOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ShenghuoOrderEntity() {

	}

	public ShenghuoOrderEntity(T t) {
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
     * 订单编号
     */
    @ColumnInfo(comment="订单编号",type="varchar(200)")
    @TableField(value = "shenghuo_order_uuid_number")

    private String shenghuoOrderUuidNumber;


    /**
     * 生活照料
     */
    @ColumnInfo(comment="生活照料",type="int(11)")
    @TableField(value = "shenghuo_id")

    private Integer shenghuoId;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 订单类型
     */
    @ColumnInfo(comment="订单类型",type="int(11)")
    @TableField(value = "shenghuo_order_types")

    private Integer shenghuoOrderTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="订单创建时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间  listShow
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
	 * 获取：订单编号
	 */
    public String getShenghuoOrderUuidNumber() {
        return shenghuoOrderUuidNumber;
    }
    /**
	 * 设置：订单编号
	 */

    public void setShenghuoOrderUuidNumber(String shenghuoOrderUuidNumber) {
        this.shenghuoOrderUuidNumber = shenghuoOrderUuidNumber;
    }
    /**
	 * 获取：生活照料
	 */
    public Integer getShenghuoId() {
        return shenghuoId;
    }
    /**
	 * 设置：生活照料
	 */

    public void setShenghuoId(Integer shenghuoId) {
        this.shenghuoId = shenghuoId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getShenghuoOrderTypes() {
        return shenghuoOrderTypes;
    }
    /**
	 * 设置：订单类型
	 */

    public void setShenghuoOrderTypes(Integer shenghuoOrderTypes) {
        this.shenghuoOrderTypes = shenghuoOrderTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ShenghuoOrder{" +
            ", id=" + id +
            ", shenghuoOrderUuidNumber=" + shenghuoOrderUuidNumber +
            ", shenghuoId=" + shenghuoId +
            ", yonghuId=" + yonghuId +
            ", shenghuoOrderTypes=" + shenghuoOrderTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
