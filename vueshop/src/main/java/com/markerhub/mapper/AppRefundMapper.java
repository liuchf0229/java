package com.markerhub.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.entity.AppCartItem;
import com.markerhub.entity.AppRefund;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Entity com.markerhub.entity.AppRefund
 */
public interface AppRefundMapper extends BaseMapper<AppRefund> {

	Page<AppRefund> pageWithUsername(Page page, @Param(Constants.WRAPPER) QueryWrapper<AppRefund> wrapper);
}




