package com.markerhub.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.markerhub.entity.AppCartItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.markerhub.entity.AppCartItem
 */
public interface AppCartItemMapper extends BaseMapper<AppCartItem> {

	List<AppCartItem> getCartItemsWithProductInfo(@Param(Constants.WRAPPER) QueryWrapper<AppCartItem> wrapper);
}




