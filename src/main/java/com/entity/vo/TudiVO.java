package com.entity.vo;

import com.entity.TudiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 土地
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("tudi")
public class TudiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 商家
     */

    @TableField(value = "laoren_id")
    private Integer laorenId;


    /**
     * 土地名称
     */

    @TableField(value = "tudi_name")
    private String tudiName;


    /**
     * 土地编号
     */

    @TableField(value = "tudi_uuid_number")
    private String tudiUuidNumber;


    /**
     * 土地照片
     */

    @TableField(value = "tudi_photo")
    private String tudiPhoto;


    /**
     * 土地地点
     */

    @TableField(value = "tudi_address")
    private String tudiAddress;


    /**
     * 土地类型
     */

    @TableField(value = "tudi_types")
    private Integer tudiTypes;


    /**
     * 多少亩
     */

    @TableField(value = "tudi_kucun_number")
    private Integer tudiKucunNumber;


    /**
     * 价格/年
     */

    @TableField(value = "tudi_new_money")
    private Double tudiNewMoney;


    /**
     * 土地介绍
     */

    @TableField(value = "tudi_content")
    private String tudiContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "tudi_delete")
    private Integer tudiDelete;


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
	 * 设置：商家
	 */
    public Integer getLaorenId() {
        return laorenId;
    }


    /**
	 * 获取：商家
	 */

    public void setLaorenId(Integer laorenId) {
        this.laorenId = laorenId;
    }
    /**
	 * 设置：土地名称
	 */
    public String getTudiName() {
        return tudiName;
    }


    /**
	 * 获取：土地名称
	 */

    public void setTudiName(String tudiName) {
        this.tudiName = tudiName;
    }
    /**
	 * 设置：土地编号
	 */
    public String getTudiUuidNumber() {
        return tudiUuidNumber;
    }


    /**
	 * 获取：土地编号
	 */

    public void setTudiUuidNumber(String tudiUuidNumber) {
        this.tudiUuidNumber = tudiUuidNumber;
    }
    /**
	 * 设置：土地照片
	 */
    public String getTudiPhoto() {
        return tudiPhoto;
    }


    /**
	 * 获取：土地照片
	 */

    public void setTudiPhoto(String tudiPhoto) {
        this.tudiPhoto = tudiPhoto;
    }
    /**
	 * 设置：土地地点
	 */
    public String getTudiAddress() {
        return tudiAddress;
    }


    /**
	 * 获取：土地地点
	 */

    public void setTudiAddress(String tudiAddress) {
        this.tudiAddress = tudiAddress;
    }
    /**
	 * 设置：土地类型
	 */
    public Integer getTudiTypes() {
        return tudiTypes;
    }


    /**
	 * 获取：土地类型
	 */

    public void setTudiTypes(Integer tudiTypes) {
        this.tudiTypes = tudiTypes;
    }
    /**
	 * 设置：多少亩
	 */
    public Integer getTudiKucunNumber() {
        return tudiKucunNumber;
    }


    /**
	 * 获取：多少亩
	 */

    public void setTudiKucunNumber(Integer tudiKucunNumber) {
        this.tudiKucunNumber = tudiKucunNumber;
    }
    /**
	 * 设置：价格/年
	 */
    public Double getTudiNewMoney() {
        return tudiNewMoney;
    }


    /**
	 * 获取：价格/年
	 */

    public void setTudiNewMoney(Double tudiNewMoney) {
        this.tudiNewMoney = tudiNewMoney;
    }
    /**
	 * 设置：土地介绍
	 */
    public String getTudiContent() {
        return tudiContent;
    }


    /**
	 * 获取：土地介绍
	 */

    public void setTudiContent(String tudiContent) {
        this.tudiContent = tudiContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getTudiDelete() {
        return tudiDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setTudiDelete(Integer tudiDelete) {
        this.tudiDelete = tudiDelete;
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
