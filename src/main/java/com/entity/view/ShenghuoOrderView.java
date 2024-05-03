package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ShenghuoOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 生活照料订单
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("shenghuo_order")
public class ShenghuoOrderView extends ShenghuoOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 订单类型的值
	*/
	@ColumnInfo(comment="订单类型的字典表值",type="varchar(200)")
	private String shenghuoOrderValue;

	//级联表 生活照料
					 
		/**
		* 生活照料 的 老人
		*/
		@ColumnInfo(comment="老人",type="int(11)")
		private Integer shenghuoLaorenId;
		/**
		* 生活照料名称
		*/

		@ColumnInfo(comment="生活照料名称",type="varchar(200)")
		private String shenghuoName;
		/**
		* 生活照料编号
		*/

		@ColumnInfo(comment="生活照料编号",type="varchar(200)")
		private String shenghuoUuidNumber;
		/**
		* 生活照料照片
		*/

		@ColumnInfo(comment="生活照料照片",type="varchar(200)")
		private String shenghuoPhoto;
		/**
		* 生活照料地点
		*/

		@ColumnInfo(comment="生活照料地点",type="varchar(200)")
		private String shenghuoAddress;
		/**
		* 生活照料类型
		*/
		@ColumnInfo(comment="生活照料类型",type="int(11)")
		private Integer shenghuoTypes;
			/**
			* 生活照料类型的值
			*/
			@ColumnInfo(comment="生活照料类型的字典表值",type="varchar(200)")
			private String shenghuoValue;
		/**
		* 生活照料介绍
		*/

		@ColumnInfo(comment="生活照料介绍",type="longtext")
		private String shenghuoContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer shenghuoDelete;
	//级联表 乡村志愿者
		/**
		* 志愿者姓名
		*/

		@ColumnInfo(comment="志愿者姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 志愿者手机号
		*/

		@ColumnInfo(comment="志愿者手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 志愿者身份证号
		*/

		@ColumnInfo(comment="志愿者身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 志愿者头像
		*/

		@ColumnInfo(comment="志愿者头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 余额
		*/
		@ColumnInfo(comment="余额",type="decimal(10,2)")
		private Double newMoney;
		/**
		* 志愿者邮箱
		*/

		@ColumnInfo(comment="志愿者邮箱",type="varchar(200)")
		private String yonghuEmail;



	public ShenghuoOrderView() {

	}

	public ShenghuoOrderView(ShenghuoOrderEntity shenghuoOrderEntity) {
		try {
			BeanUtils.copyProperties(this, shenghuoOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 订单类型的值
	*/
	public String getShenghuoOrderValue() {
		return shenghuoOrderValue;
	}
	/**
	* 设置： 订单类型的值
	*/
	public void setShenghuoOrderValue(String shenghuoOrderValue) {
		this.shenghuoOrderValue = shenghuoOrderValue;
	}


	//级联表的get和set 生活照料
		/**
		* 获取：生活照料 的 老人
		*/
		public Integer getShenghuoLaorenId() {
			return shenghuoLaorenId;
		}
		/**
		* 设置：生活照料 的 老人
		*/
		public void setShenghuoLaorenId(Integer shenghuoLaorenId) {
			this.shenghuoLaorenId = shenghuoLaorenId;
		}

		/**
		* 获取： 生活照料名称
		*/
		public String getShenghuoName() {
			return shenghuoName;
		}
		/**
		* 设置： 生活照料名称
		*/
		public void setShenghuoName(String shenghuoName) {
			this.shenghuoName = shenghuoName;
		}

		/**
		* 获取： 生活照料编号
		*/
		public String getShenghuoUuidNumber() {
			return shenghuoUuidNumber;
		}
		/**
		* 设置： 生活照料编号
		*/
		public void setShenghuoUuidNumber(String shenghuoUuidNumber) {
			this.shenghuoUuidNumber = shenghuoUuidNumber;
		}

		/**
		* 获取： 生活照料照片
		*/
		public String getShenghuoPhoto() {
			return shenghuoPhoto;
		}
		/**
		* 设置： 生活照料照片
		*/
		public void setShenghuoPhoto(String shenghuoPhoto) {
			this.shenghuoPhoto = shenghuoPhoto;
		}

		/**
		* 获取： 生活照料地点
		*/
		public String getShenghuoAddress() {
			return shenghuoAddress;
		}
		/**
		* 设置： 生活照料地点
		*/
		public void setShenghuoAddress(String shenghuoAddress) {
			this.shenghuoAddress = shenghuoAddress;
		}
		/**
		* 获取： 生活照料类型
		*/
		public Integer getShenghuoTypes() {
			return shenghuoTypes;
		}
		/**
		* 设置： 生活照料类型
		*/
		public void setShenghuoTypes(Integer shenghuoTypes) {
			this.shenghuoTypes = shenghuoTypes;
		}


			/**
			* 获取： 生活照料类型的值
			*/
			public String getShenghuoValue() {
				return shenghuoValue;
			}
			/**
			* 设置： 生活照料类型的值
			*/
			public void setShenghuoValue(String shenghuoValue) {
				this.shenghuoValue = shenghuoValue;
			}

		/**
		* 获取： 生活照料介绍
		*/
		public String getShenghuoContent() {
			return shenghuoContent;
		}
		/**
		* 设置： 生活照料介绍
		*/
		public void setShenghuoContent(String shenghuoContent) {
			this.shenghuoContent = shenghuoContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getShenghuoDelete() {
			return shenghuoDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setShenghuoDelete(Integer shenghuoDelete) {
			this.shenghuoDelete = shenghuoDelete;
		}
	//级联表的get和set 乡村志愿者

		/**
		* 获取： 志愿者姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 志愿者姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 志愿者手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 志愿者手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 志愿者身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 志愿者身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 志愿者头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 志愿者头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 余额
		*/
		public Double getNewMoney() {
			return newMoney;
		}
		/**
		* 设置： 余额
		*/
		public void setNewMoney(Double newMoney) {
			this.newMoney = newMoney;
		}

		/**
		* 获取： 志愿者邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 志愿者邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}


	@Override
	public String toString() {
		return "ShenghuoOrderView{" +
			", shenghuoOrderValue=" + shenghuoOrderValue +
			", shenghuoName=" + shenghuoName +
			", shenghuoUuidNumber=" + shenghuoUuidNumber +
			", shenghuoPhoto=" + shenghuoPhoto +
			", shenghuoAddress=" + shenghuoAddress +
			", shenghuoContent=" + shenghuoContent +
			", shenghuoDelete=" + shenghuoDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", newMoney=" + newMoney +
			", yonghuEmail=" + yonghuEmail +
			"} " + super.toString();
	}
}
