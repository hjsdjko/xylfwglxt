package com.dao;

import com.entity.ShenghuoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ShenghuoView;

/**
 * 生活照料 Dao 接口
 *
 * @author 
 */
public interface ShenghuoDao extends BaseMapper<ShenghuoEntity> {

   List<ShenghuoView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
