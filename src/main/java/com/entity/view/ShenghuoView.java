package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ShenghuoEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 生活照料
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("shenghuo")
public class ShenghuoView extends ShenghuoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 生活照料类型的值
	*/
	@ColumnInfo(comment="生活照料类型的字典表值",type="varchar(200)")
	private String shenghuoValue;

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



	public ShenghuoView() {

	}

	public ShenghuoView(ShenghuoEntity shenghuoEntity) {
		try {
			BeanUtils.copyProperties(this, shenghuoEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
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
		return "ShenghuoView{" +
			", shenghuoValue=" + shenghuoValue +
			", laorenName=" + laorenName +
			", laorenPhone=" + laorenPhone +
			", laorenEmail=" + laorenEmail +
			", newMoney=" + newMoney +
			", laorenContent=" + laorenContent +
			", laorenDelete=" + laorenDelete +
			"} " + super.toString();
	}
}
