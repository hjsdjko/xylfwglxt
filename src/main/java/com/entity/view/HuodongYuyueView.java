package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.HuodongYuyueEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 活动报名
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("huodong_yuyue")
public class HuodongYuyueView extends HuodongYuyueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 报名状态的值
	*/
	@ColumnInfo(comment="报名状态的字典表值",type="varchar(200)")
	private String huodongYuyueYesnoValue;

	//级联表 文娱活动
		/**
		* 文娱活动名称
		*/

		@ColumnInfo(comment="文娱活动名称",type="varchar(200)")
		private String huodongName;
		/**
		* 文娱活动编号
		*/

		@ColumnInfo(comment="文娱活动编号",type="varchar(200)")
		private String huodongUuidNumber;
		/**
		* 文娱活动照片
		*/

		@ColumnInfo(comment="文娱活动照片",type="varchar(200)")
		private String huodongPhoto;
		/**
		* 文娱活动地点
		*/

		@ColumnInfo(comment="文娱活动地点",type="varchar(200)")
		private String huodongAddress;
		/**
		* 文娱活动类型
		*/
		@ColumnInfo(comment="文娱活动类型",type="int(11)")
		private Integer huodongTypes;
			/**
			* 文娱活动类型的值
			*/
			@ColumnInfo(comment="文娱活动类型的字典表值",type="varchar(200)")
			private String huodongValue;
		/**
		* 文娱活动结束
		*/

		@ColumnInfo(comment="文娱活动结束",type="longtext")
		private String huodongContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer huodongDelete;
	//级联表 老人
		/**
		* 老人名称
		*/

		@ColumnInfo(comment="老人名称",type="varchar(200)")
		private String laorenName;
		/**
		* 联系方式
		*/

		@ColumnInfo(comment="联系方式",type="varchar(200)")
		private String laorenPhone;
		/**
		* 邮箱
		*/

		@ColumnInfo(comment="邮箱",type="varchar(200)")
		private String laorenEmail;
		/**
		* 余额
		*/
		@ColumnInfo(comment="余额",type="decimal(10,2)")
		private Double newMoney;
		/**
		* 老人介绍
		*/

		@ColumnInfo(comment="老人介绍",type="longtext")
		private String laorenContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer laorenDelete;



	public HuodongYuyueView() {

	}

	public HuodongYuyueView(HuodongYuyueEntity huodongYuyueEntity) {
		try {
			BeanUtils.copyProperties(this, huodongYuyueEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 报名状态的值
	*/
	public String getHuodongYuyueYesnoValue() {
		return huodongYuyueYesnoValue;
	}
	/**
	* 设置： 报名状态的值
	*/
	public void setHuodongYuyueYesnoValue(String huodongYuyueYesnoValue) {
		this.huodongYuyueYesnoValue = huodongYuyueYesnoValue;
	}


	//级联表的get和set 文娱活动

		/**
		* 获取： 文娱活动名称
		*/
		public String getHuodongName() {
			return huodongName;
		}
		/**
		* 设置： 文娱活动名称
		*/
		public void setHuodongName(String huodongName) {
			this.huodongName = huodongName;
		}

		/**
		* 获取： 文娱活动编号
		*/
		public String getHuodongUuidNumber() {
			return huodongUuidNumber;
		}
		/**
		* 设置： 文娱活动编号
		*/
		public void setHuodongUuidNumber(String huodongUuidNumber) {
			this.huodongUuidNumber = huodongUuidNumber;
		}

		/**
		* 获取： 文娱活动照片
		*/
		public String getHuodongPhoto() {
			return huodongPhoto;
		}
		/**
		* 设置： 文娱活动照片
		*/
		public void setHuodongPhoto(String huodongPhoto) {
			this.huodongPhoto = huodongPhoto;
		}

		/**
		* 获取： 文娱活动地点
		*/
		public String getHuodongAddress() {
			return huodongAddress;
		}
		/**
		* 设置： 文娱活动地点
		*/
		public void setHuodongAddress(String huodongAddress) {
			this.huodongAddress = huodongAddress;
		}
		/**
		* 获取： 文娱活动类型
		*/
		public Integer getHuodongTypes() {
			return huodongTypes;
		}
		/**
		* 设置： 文娱活动类型
		*/
		public void setHuodongTypes(Integer huodongTypes) {
			this.huodongTypes = huodongTypes;
		}


			/**
			* 获取： 文娱活动类型的值
			*/
			public String getHuodongValue() {
				return huodongValue;
			}
			/**
			* 设置： 文娱活动类型的值
			*/
			public void setHuodongValue(String huodongValue) {
				this.huodongValue = huodongValue;
			}

		/**
		* 获取： 文娱活动结束
		*/
		public String getHuodongContent() {
			return huodongContent;
		}
		/**
		* 设置： 文娱活动结束
		*/
		public void setHuodongContent(String huodongContent) {
			this.huodongContent = huodongContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getHuodongDelete() {
			return huodongDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setHuodongDelete(Integer huodongDelete) {
			this.huodongDelete = huodongDelete;
		}
	//级联表的get和set 老人

		/**
		* 获取： 老人名称
		*/
		public String getLaorenName() {
			return laorenName;
		}
		/**
		* 设置： 老人名称
		*/
		public void setLaorenName(String laorenName) {
			this.laorenName = laorenName;
		}

		/**
		* 获取： 联系方式
		*/
		public String getLaorenPhone() {
			return laorenPhone;
		}
		/**
		* 设置： 联系方式
		*/
		public void setLaorenPhone(String laorenPhone) {
			this.laorenPhone = laorenPhone;
		}

		/**
		* 获取： 邮箱
		*/
		public String getLaorenEmail() {
			return laorenEmail;
		}
		/**
		* 设置： 邮箱
		*/
		public void setLaorenEmail(String laorenEmail) {
			this.laorenEmail = laorenEmail;
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
		* 获取： 老人介绍
		*/
		public String getLaorenContent() {
			return laorenContent;
		}
		/**
		* 设置： 老人介绍
		*/
		public void setLaorenContent(String laorenContent) {
			this.laorenContent = laorenContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getLaorenDelete() {
			return laorenDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setLaorenDelete(Integer laorenDelete) {
			this.laorenDelete = laorenDelete;
		}


	@Override
	public String toString() {
		return "HuodongYuyueView{" +
			", huodongYuyueYesnoValue=" + huodongYuyueYesnoValue +
			", huodongName=" + huodongName +
			", huodongUuidNumber=" + huodongUuidNumber +
			", huodongPhoto=" + huodongPhoto +
			", huodongAddress=" + huodongAddress +
			", huodongContent=" + huodongContent +
			", huodongDelete=" + huodongDelete +
			", laorenName=" + laorenName +
			", laorenPhone=" + laorenPhone +
			", laorenEmail=" + laorenEmail +
			", newMoney=" + newMoney +
			", laorenContent=" + laorenContent +
			", laorenDelete=" + laorenDelete +
			"} " + super.toString();
	}
}
