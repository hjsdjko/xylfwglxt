package com.entity.vo;

import com.entity.TudiOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 土地承包
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("tudi_order")
public class TudiOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单编号
     */

    @TableField(value = "tudi_order_uuid_number")
    private String tudiOrderUuidNumber;


    /**
     * 土地
     */

    @TableField(value = "tudi_id")
    private Integer tudiId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 亩
     */

    @TableField(value = "buy_number")
    private Integer buyNumber;


    /**
     * 订单类型
     */

    @TableField(value = "tudi_order_types")
    private Integer tudiOrderTypes;


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
    public String getTudiOrderUuidNumber() {
        return tudiOrderUuidNumber;
    }


    /**
	 * 获取：订单编号
	 */

    public void setTudiOrderUuidNumber(String tudiOrderUuidNumber) {
        this.tudiOrderUuidNumber = tudiOrderUuidNumber;
    }
    /**
	 * 设置：土地
	 */
    public Integer getTudiId() {
        return tudiId;
    }


    /**
	 * 获取：土地
	 */

    public void setTudiId(Integer tudiId) {
        this.tudiId = tudiId;
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
	 * 设置：亩
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 获取：亩
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getTudiOrderTypes() {
        return tudiOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setTudiOrderTypes(Integer tudiOrderTypes) {
        this.tudiOrderTypes = tudiOrderTypes;
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
