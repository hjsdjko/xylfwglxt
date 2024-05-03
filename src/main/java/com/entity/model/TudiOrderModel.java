package com.entity.model;

import com.entity.TudiOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 土地承包
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class TudiOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单编号
     */
    private String tudiOrderUuidNumber;


    /**
     * 土地
     */
    private Integer tudiId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 亩
     */
    private Integer buyNumber;


    /**
     * 订单类型
     */
    private Integer tudiOrderTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
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
	 * 获取：订单编号
	 */
    public String getTudiOrderUuidNumber() {
        return tudiOrderUuidNumber;
    }


    /**
	 * 设置：订单编号
	 */
    public void setTudiOrderUuidNumber(String tudiOrderUuidNumber) {
        this.tudiOrderUuidNumber = tudiOrderUuidNumber;
    }
    /**
	 * 获取：土地
	 */
    public Integer getTudiId() {
        return tudiId;
    }


    /**
	 * 设置：土地
	 */
    public void setTudiId(Integer tudiId) {
        this.tudiId = tudiId;
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
	 * 获取：亩
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 设置：亩
	 */
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getTudiOrderTypes() {
        return tudiOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setTudiOrderTypes(Integer tudiOrderTypes) {
        this.tudiOrderTypes = tudiOrderTypes;
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
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
