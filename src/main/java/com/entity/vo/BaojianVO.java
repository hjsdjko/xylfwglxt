package com.entity.vo;

import com.entity.BaojianEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 医疗保健
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("baojian")
public class BaojianVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 医疗人员
     */

    @TableField(value = "yiliao_id")
    private Integer yiliaoId;


    /**
     * 老人
     */

    @TableField(value = "laoren_id")
    private Integer laorenId;


    /**
     * 医疗保健名称
     */

    @TableField(value = "baojian_name")
    private String baojianName;


    /**
     * 医疗保健编号
     */

    @TableField(value = "baojian_uuid_number")
    private String baojianUuidNumber;


    /**
     * 医疗保健照片
     */

    @TableField(value = "baojian_photo")
    private String baojianPhoto;


    /**
     * 医疗保健地点
     */

    @TableField(value = "baojian_address")
    private String baojianAddress;


    /**
     * 医疗保健类型
     */

    @TableField(value = "baojian_types")
    private Integer baojianTypes;


    /**
     * 注意事项
     */

    @TableField(value = "baojian_content")
    private String baojianContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "baojian_delete")
    private Integer baojianDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


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
	 * 设置：医疗人员
	 */
    public Integer getYiliaoId() {
        return yiliaoId;
    }


    /**
	 * 获取：医疗人员
	 */

    public void setYiliaoId(Integer yiliaoId) {
        this.yiliaoId = yiliaoId;
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
	 * 设置：医疗保健名称
	 */
    public String getBaojianName() {
        return baojianName;
    }


    /**
	 * 获取：医疗保健名称
	 */

    public void setBaojianName(String baojianName) {
        this.baojianName = baojianName;
    }
    /**
	 * 设置：医疗保健编号
	 */
    public String getBaojianUuidNumber() {
        return baojianUuidNumber;
    }


    /**
	 * 获取：医疗保健编号
	 */

    public void setBaojianUuidNumber(String baojianUuidNumber) {
        this.baojianUuidNumber = baojianUuidNumber;
    }
    /**
	 * 设置：医疗保健照片
	 */
    public String getBaojianPhoto() {
        return baojianPhoto;
    }


    /**
	 * 获取：医疗保健照片
	 */

    public void setBaojianPhoto(String baojianPhoto) {
        this.baojianPhoto = baojianPhoto;
    }
    /**
	 * 设置：医疗保健地点
	 */
    public String getBaojianAddress() {
        return baojianAddress;
    }


    /**
	 * 获取：医疗保健地点
	 */

    public void setBaojianAddress(String baojianAddress) {
        this.baojianAddress = baojianAddress;
    }
    /**
	 * 设置：医疗保健类型
	 */
    public Integer getBaojianTypes() {
        return baojianTypes;
    }


    /**
	 * 获取：医疗保健类型
	 */

    public void setBaojianTypes(Integer baojianTypes) {
        this.baojianTypes = baojianTypes;
    }
    /**
	 * 设置：注意事项
	 */
    public String getBaojianContent() {
        return baojianContent;
    }


    /**
	 * 获取：注意事项
	 */

    public void setBaojianContent(String baojianContent) {
        this.baojianContent = baojianContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getBaojianDelete() {
        return baojianDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setBaojianDelete(Integer baojianDelete) {
        this.baojianDelete = baojianDelete;
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
