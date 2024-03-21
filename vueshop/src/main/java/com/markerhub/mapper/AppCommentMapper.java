package com.markerhub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.entity.AppComment;

/**
 * @Entity com.markerhub.entity.AppComment
 */
public interface AppCommentMapper extends BaseMapper<AppComment> {

	Page<AppComment> pageByProductId(Page page, long productId);
}




