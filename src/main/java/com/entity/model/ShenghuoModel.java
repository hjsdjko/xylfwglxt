package com.entity.model;

import com.entity.ShenghuoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 生活照料
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ShenghuoModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 老人
     */
    private Integer laorenId;


    /**
     * 生活照料名称
     */
    private String shenghuoName;


    /**
     * 生活照料编号
     */
    private String shenghuoUuidNumber;


    /**
     * 生活照料照片
     */
    private String shenghuoPhoto;


    /**
     * 生活照料地点
     */
    private String shenghuoAddress;


    /**
     * 生活照料类型
     */
    private Integer shenghuoTypes;


    /**
     * 生活照料介绍
     */
    private String shenghuoContent;


    /**
     * 逻辑删除
     */
    private Integer shenghuoDelete;


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
	 * 获取：老人
	 */
    public Integer getLaorenId() {
        return laorenId;
    }


    /**
	 * 设置：老人
	 */
    public void setLaorenId(Integer laorenId) {
        this.laorenId = laorenId;
    }
    /**
	 * 获取：生活照料名称
	 */
    public String getShenghuoName() {
        return shenghuoName;
    }


    /**
	 * 设置：生活照料名称
	 */
    public void setShenghuoName(String shenghuoName) {
        this.shenghuoName = shenghuoName;
    }
    /**
	 * 获取：生活照料编号
	 */
    public String getShenghuoUuidNumber() {
        return shenghuoUuidNumber;
    }


    /**
	 * 设置：生活照料编号
	 */
    public void setShenghuoUuidNumber(String shenghuoUuidNumber) {
        this.shenghuoUuidNumber = shenghuoUuidNumber;
    }
    /**
	 * 获取：生活照料照片
	 */
    public String getShenghuoPhoto() {
        return shenghuoPhoto;
    }


    /**
	 * 设置：生活照料照片
	 */
    public void setShenghuoPhoto(String shenghuoPhoto) {
        this.shenghuoPhoto = shenghuoPhoto;
    }
    /**
	 * 获取：生活照料地点
	 */
    public String getShenghuoAddress() {
        return shenghuoAddress;
    }


    /**
	 * 设置：生活照料地点
	 */
    public void setShenghuoAddress(String shenghuoAddress) {
        this.shenghuoAddress = shenghuoAddress;
    }
    /**
	 * 获取：生活照料类型
	 */
    public Integer getShenghuoTypes() {
        return shenghuoTypes;
    }


    /**
	 * 设置：生活照料类型
	 */
    public void setShenghuoTypes(Integer shenghuoTypes) {
        this.shenghuoTypes = shenghuoTypes;
    }
    /**
	 * 获取：生活照料介绍
	 */
    public String getShenghuoContent() {
        return shenghuoContent;
    }


    /**
	 * 设置：生活照料介绍
	 */
    public void setShenghuoContent(String shenghuoContent) {
        this.shenghuoContent = shenghuoContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getShenghuoDelete() {
        return shenghuoDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setShenghuoDelete(Integer shenghuoDelete) {
        this.shenghuoDelete = shenghuoDelete;
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
