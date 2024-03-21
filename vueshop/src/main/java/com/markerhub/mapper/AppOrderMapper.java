package com.markerhub.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.entity.AppOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity com.markerhub.entity.AppOrder
 */
public interface AppOrderMapper extends BaseMapper<AppOrder> {

	Page<AppOrder> pageWithUsername(Page page, AppOrder appOrder);
}




