package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.JiankangEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 健康档案
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("jiankang")
public class JiankangView extends JiankangEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 身体情况的值
	*/
	@ColumnInfo(comment="身体情况的字典表值",type="varchar(200)")
	private String jiankangValue;

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
	//级联表 医疗人员
		/**
		* 医疗人员名称
		*/

		@ColumnInfo(comment="医疗人员名称",type="varchar(200)")
		private String yiliaoName;
		/**
		* 联系方式
		*/

		@ColumnInfo(comment="联系方式",type="varchar(200)")
		private String yiliaoPhone;
		/**
		* 邮箱
		*/

		@ColumnInfo(comment="邮箱",type="varchar(200)")
		private String yiliaoEmail;
		/**
		* 医疗人员介绍
		*/

		@ColumnInfo(comment="医疗人员介绍",type="longtext")
		private String yiliaoContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer yiliaoDelete;

	//重复字段
			/**
			* 重复字段 的types
			*/
			@ColumnInfo(comment="重复字段 的types",type="Integer")
			private Integer sexTypes;
				@ColumnInfo(comment="重复字段 的值",type="varchar(200)")
				private String sexValue;


	public JiankangView() {

	}

	public JiankangView(JiankangEntity jiankangEntity) {
		try {
			BeanUtils.copyProperties(this, jiankangEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 身体情况的值
	*/
	public String getJiankangValue() {
		return jiankangValue;
	}
	/**
	* 设置： 身体情况的值
	*/
	public void setJiankangValue(String jiankangValue) {
		this.jiankangValue = jiankangValue;
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
	//级联表的get和set 医疗人员

		/**
		* 获取： 医疗人员名称
		*/
		public String getYiliaoName() {
			return yiliaoName;
		}
		/**
		* 设置： 医疗人员名称
		*/
		public void setYiliaoName(String yiliaoName) {
			this.yiliaoName = yiliaoName;
		}

		/**
		* 获取： 联系方式
		*/
		public String getYiliaoPhone() {
			return yiliaoPhone;
		}
		/**
		* 设置： 联系方式
		*/
		public void setYiliaoPhone(String yiliaoPhone) {
			this.yiliaoPhone = yiliaoPhone;
		}

		/**
		* 获取： 邮箱
		*/
		public String getYiliaoEmail() {
			return yiliaoEmail;
		}
		/**
		* 设置： 邮箱
		*/
		public void setYiliaoEmail(String yiliaoEmail) {
			this.yiliaoEmail = yiliaoEmail;
		}

		/**
		* 获取： 医疗人员介绍
		*/
		public String getYiliaoContent() {
			return yiliaoContent;
		}
		/**
		* 设置： 医疗人员介绍
		*/
		public void setYiliaoContent(String yiliaoContent) {
			this.yiliaoContent = yiliaoContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getYiliaoDelete() {
			return yiliaoDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setYiliaoDelete(Integer yiliaoDelete) {
			this.yiliaoDelete = yiliaoDelete;
		}

	//重复字段
			/**
			* 获取： 重复字段 的types
			*/
			public Integer getSexTypes() {
			return sexTypes;
			}
			/**
			* 设置： 重复字段 的types
			*/
			public void setSexTypes(Integer sexTypes) {
			this.sexTypes = sexTypes;
			}
				public String getSexValue() {
				return sexValue;
				}
				public void setSexValue(String sexValue) {
				this.sexValue = sexValue;
				}

	@Override
	public String toString() {
		return "JiankangView{" +
			", jiankangValue=" + jiankangValue +
			", yiliaoName=" + yiliaoName +
			", yiliaoPhone=" + yiliaoPhone +
			", yiliaoEmail=" + yiliaoEmail +
			", yiliaoContent=" + yiliaoContent +
			", yiliaoDelete=" + yiliaoDelete +
			", laorenName=" + laorenName +
			", laorenPhone=" + laorenPhone +
			", laorenEmail=" + laorenEmail +
			", newMoney=" + newMoney +
			", laorenContent=" + laorenContent +
			", laorenDelete=" + laorenDelete +
			"} " + super.toString();
	}
}
