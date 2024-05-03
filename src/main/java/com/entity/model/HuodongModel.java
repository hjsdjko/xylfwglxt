package com.entity.model;

import com.entity.HuodongEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 文娱活动
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class HuodongModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 文娱活动名称
     */
    private String huodongName;


    /**
     * 文娱活动编号
     */
    private String huodongUuidNumber;


    /**
     * 文娱活动照片
     */
    private String huodongPhoto;


    /**
     * 文娱活动地点
     */
    private String huodongAddress;


    /**
     * 文娱活动类型
     */
    private Integer huodongTypes;


    /**
     * 文娱活动结束
     */
    private String huodongContent;


    /**
     * 逻辑删除
     */
    private Integer huodongDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：文娱活动名称
	 */
    public String getHuodongName() {
        return huodongName;
    }


    /**
	 * 设置：文娱活动名称
	 */
    public void setHuodongName(String huodongName) {
        this.huodongName = huodongName;
    }
    /**
	 * 获取：文娱活动编号
	 */
    public String getHuodongUuidNumber() {
        return huodongUuidNumber;
    }


    /**
	 * 设置：文娱活动编号
	 */
    public void setHuodongUuidNumber(String huodongUuidNumber) {
        this.huodongUuidNumber = huodongUuidNumber;
    }
    /**
	 * 获取：文娱活动照片
	 */
    public String getHuodongPhoto() {
        return huodongPhoto;
    }


    /**
	 * 设置：文娱活动照片
	 */
    public void setHuodongPhoto(String huodongPhoto) {
        this.huodongPhoto = huodongPhoto;
    }
    /**
	 * 获取：文娱活动地点
	 */
    public String getHuodongAddress() {
        return huodongAddress;
    }


    /**
	 * 设置：文娱活动地点
	 */
    public void setHuodongAddress(String huodongAddress) {
        this.huodongAddress = huodongAddress;
    }
    /**
	 * 获取：文娱活动类型
	 */
    public Integer getHuodongTypes() {
        return huodongTypes;
    }


    /**
	 * 设置：文娱活动类型
	 */
    public void setHuodongTypes(Integer huodongTypes) {
        this.huodongTypes = huodongTypes;
    }
    /**
	 * 获取：文娱活动结束
	 */
    public String getHuodongContent() {
        return huodongContent;
    }


    /**
	 * 设置：文娱活动结束
	 */
    public void setHuodongContent(String huodongContent) {
        this.huodongContent = huodongContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getHuodongDelete() {
        return huodongDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setHuodongDelete(Integer huodongDelete) {
        this.huodongDelete = huodongDelete;
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
