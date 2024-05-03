package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.TudiOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 土地承包
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("tudi_order")
public class TudiOrderView extends TudiOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 订单类型的值
	*/
	@ColumnInfo(comment="订单类型的字典表值",type="varchar(200)")
	private String tudiOrderValue;

	//级联表 土地
					 
		/**
		* 土地 的 商家
		*/
		@ColumnInfo(comment="商家",type="int(11)")
		private Integer tudiLaorenId;
		/**
		* 土地名称
		*/

		@ColumnInfo(comment="土地名称",type="varchar(200)")
		private String tudiName;
		/**
		* 土地编号
		*/

		@ColumnInfo(comment="土地编号",type="varchar(200)")
		private String tudiUuidNumber;
		/**
		* 土地照片
		*/

		@ColumnInfo(comment="土地照片",type="varchar(200)")
		private String tudiPhoto;
		/**
		* 土地地点
		*/

		@ColumnInfo(comment="土地地点",type="varchar(200)")
		private String tudiAddress;
		/**
		* 土地类型
		*/
		@ColumnInfo(comment="土地类型",type="int(11)")
		private Integer tudiTypes;
			/**
			* 土地类型的值
			*/
			@ColumnInfo(comment="土地类型的字典表值",type="varchar(200)")
			private String tudiValue;
		/**
		* 多少亩
		*/

		@ColumnInfo(comment="多少亩",type="int(11)")
		private Integer tudiKucunNumber;
		/**
		* 价格/年
		*/
		@ColumnInfo(comment="价格/年",type="decimal(10,2)")
		private Double tudiNewMoney;
		/**
		* 土地介绍
		*/

		@ColumnInfo(comment="土地介绍",type="longtext")
		private String tudiContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer tudiDelete;
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



	public TudiOrderView() {

	}

	public TudiOrderView(TudiOrderEntity tudiOrderEntity) {
		try {
			BeanUtils.copyProperties(this, tudiOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 订单类型的值
	*/
	public String getTudiOrderValue() {
		return tudiOrderValue;
	}
	/**
	* 设置： 订单类型的值
	*/
	public void setTudiOrderValue(String tudiOrderValue) {
		this.tudiOrderValue = tudiOrderValue;
	}


	//级联表的get和set 土地
		/**
		* 获取：土地 的 商家
		*/
		public Integer getTudiLaorenId() {
			return tudiLaorenId;
		}
		/**
		* 设置：土地 的 商家
		*/
		public void setTudiLaorenId(Integer tudiLaorenId) {
			this.tudiLaorenId = tudiLaorenId;
		}

		/**
		* 获取： 土地名称
		*/
		public String getTudiName() {
			return tudiName;
		}
		/**
		* 设置： 土地名称
		*/
		public void setTudiName(String tudiName) {
			this.tudiName = tudiName;
		}

		/**
		* 获取： 土地编号
		*/
		public String getTudiUuidNumber() {
			return tudiUuidNumber;
		}
		/**
		* 设置： 土地编号
		*/
		public void setTudiUuidNumber(String tudiUuidNumber) {
			this.tudiUuidNumber = tudiUuidNumber;
		}

		/**
		* 获取： 土地照片
		*/
		public String getTudiPhoto() {
			return tudiPhoto;
		}
		/**
		* 设置： 土地照片
		*/
		public void setTudiPhoto(String tudiPhoto) {
			this.tudiPhoto = tudiPhoto;
		}

		/**
		* 获取： 土地地点
		*/
		public String getTudiAddress() {
			return tudiAddress;
		}
		/**
		* 设置： 土地地点
		*/
		public void setTudiAddress(String tudiAddress) {
			this.tudiAddress = tudiAddress;
		}
		/**
		* 获取： 土地类型
		*/
		public Integer getTudiTypes() {
			return tudiTypes;
		}
		/**
		* 设置： 土地类型
		*/
		public void setTudiTypes(Integer tudiTypes) {
			this.tudiTypes = tudiTypes;
		}


			/**
			* 获取： 土地类型的值
			*/
			public String getTudiValue() {
				return tudiValue;
			}
			/**
			* 设置： 土地类型的值
			*/
			public void setTudiValue(String tudiValue) {
				this.tudiValue = tudiValue;
			}

		/**
		* 获取： 多少亩
		*/
		public Integer getTudiKucunNumber() {
			return tudiKucunNumber;
		}
		/**
		* 设置： 多少亩
		*/
		public void setTudiKucunNumber(Integer tudiKucunNumber) {
			this.tudiKucunNumber = tudiKucunNumber;
		}

		/**
		* 获取： 价格/年
		*/
		public Double getTudiNewMoney() {
			return tudiNewMoney;
		}
		/**
		* 设置： 价格/年
		*/
		public void setTudiNewMoney(Double tudiNewMoney) {
			this.tudiNewMoney = tudiNewMoney;
		}

		/**
		* 获取： 土地介绍
		*/
		public String getTudiContent() {
			return tudiContent;
		}
		/**
		* 设置： 土地介绍
		*/
		public void setTudiContent(String tudiContent) {
			this.tudiContent = tudiContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getTudiDelete() {
			return tudiDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setTudiDelete(Integer tudiDelete) {
			this.tudiDelete = tudiDelete;
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
		return "TudiOrderView{" +
			", tudiOrderValue=" + tudiOrderValue +
			", tudiName=" + tudiName +
			", tudiUuidNumber=" + tudiUuidNumber +
			", tudiPhoto=" + tudiPhoto +
			", tudiAddress=" + tudiAddress +
			", tudiKucunNumber=" + tudiKucunNumber +
			", tudiNewMoney=" + tudiNewMoney +
			", tudiContent=" + tudiContent +
			", tudiDelete=" + tudiDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", newMoney=" + newMoney +
			", yonghuEmail=" + yonghuEmail +
			"} " + super.toString();
	}
}
