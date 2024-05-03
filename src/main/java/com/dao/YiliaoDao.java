package com.dao;

import com.entity.YiliaoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YiliaoView;

/**
 * 医疗人员 Dao 接口
 *
 * @author 
 */
public interface YiliaoDao extends BaseMapper<YiliaoEntity> {

   List<YiliaoView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
