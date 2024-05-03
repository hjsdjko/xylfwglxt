package com.entity.vo;

import com.entity.ShenghuoEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 生活照料
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("shenghuo")
public class ShenghuoVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 老人
     */

    @TableField(value = "laoren_id")
    private Integer laorenId;


    /**
     * 生活照料名称
     */

    @TableField(value = "shenghuo_name")
    private String shenghuoName;


    /**
     * 生活照料编号
     */

    @TableField(value = "shenghuo_uuid_number")
    private String shenghuoUuidNumber;


    /**
     * 生活照料照片
     */

    @TableField(value = "shenghuo_photo")
    private String shenghuoPhoto;


    /**
     * 生活照料地点
     */

    @TableField(value = "shenghuo_address")
    private String shenghuoAddress;


    /**
     * 生活照料类型
     */

    @TableField(value = "shenghuo_types")
    private Integer shenghuoTypes;


    /**
     * 生活照料介绍
     */

    @TableField(value = "shenghuo_content")
    private String shenghuoContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "shenghuo_delete")
    private Integer shenghuoDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：老人
	 */
    public Integer getLaorenId() {
        return laorenId;
    }


    /**
	 * 获取：老人
	 */

    public void setLaorenId(Integer laorenId) {
        this.laorenId = laorenId;
    }
    /**
	 * 设置：生活照料名称
	 */
    public String getShenghuoName() {
        return shenghuoName;
    }


    /**
	 * 获取：生活照料名称
	 */

    public void setShenghuoName(String shenghuoName) {
        this.shenghuoName = shenghuoName;
    }
    /**
	 * 设置：生活照料编号
	 */
    public String getShenghuoUuidNumber() {
        return shenghuoUuidNumber;
    }


    /**
	 * 获取：生活照料编号
	 */

    public void setShenghuoUuidNumber(String shenghuoUuidNumber) {
        this.shenghuoUuidNumber = shenghuoUuidNumber;
    }
    /**
	 * 设置：生活照料照片
	 */
    public String getShenghuoPhoto() {
        return shenghuoPhoto;
    }


    /**
	 * 获取：生活照料照片
	 */

    public void setShenghuoPhoto(String shenghuoPhoto) {
        this.shenghuoPhoto = shenghuoPhoto;
    }
    /**
	 * 设置：生活照料地点
	 */
    public String getShenghuoAddress() {
        return shenghuoAddress;
    }


    /**
	 * 获取：生活照料地点
	 */

    public void setShenghuoAddress(String shenghuoAddress) {
        this.shenghuoAddress = shenghuoAddress;
    }
    /**
	 * 设置：生活照料类型
	 */
    public Integer getShenghuoTypes() {
        return shenghuoTypes;
    }


    /**
	 * 获取：生活照料类型
	 */

    public void setShenghuoTypes(Integer shenghuoTypes) {
        this.shenghuoTypes = shenghuoTypes;
    }
    /**
	 * 设置：生活照料介绍
	 */
    public String getShenghuoContent() {
        return shenghuoContent;
    }


    /**
	 * 获取：生活照料介绍
	 */

    public void setShenghuoContent(String shenghuoContent) {
        this.shenghuoContent = shenghuoContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getShenghuoDelete() {
        return shenghuoDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setShenghuoDelete(Integer shenghuoDelete) {
        this.shenghuoDelete = shenghuoDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
