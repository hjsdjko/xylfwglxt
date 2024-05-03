package com.entity.vo;

import com.entity.ShenghuoOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 生活照料订单
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("shenghuo_order")
public class ShenghuoOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单编号
     */

    @TableField(value = "shenghuo_order_uuid_number")
    private String shenghuoOrderUuidNumber;


    /**
     * 生活照料
     */

    @TableField(value = "shenghuo_id")
    private Integer shenghuoId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 订单类型
     */

    @TableField(value = "shenghuo_order_types")
    private Integer shenghuoOrderTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
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
	 * 设置：订单编号
	 */
    public String getShenghuoOrderUuidNumber() {
        return shenghuoOrderUuidNumber;
    }


    /**
	 * 获取：订单编号
	 */

    public void setShenghuoOrderUuidNumber(String shenghuoOrderUuidNumber) {
        this.shenghuoOrderUuidNumber = shenghuoOrderUuidNumber;
    }
    /**
	 * 设置：生活照料
	 */
    public Integer getShenghuoId() {
        return shenghuoId;
    }


    /**
	 * 获取：生活照料
	 */

    public void setShenghuoId(Integer shenghuoId) {
        this.shenghuoId = shenghuoId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getShenghuoOrderTypes() {
        return shenghuoOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setShenghuoOrderTypes(Integer shenghuoOrderTypes) {
        this.shenghuoOrderTypes = shenghuoOrderTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
