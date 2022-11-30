package com.javaPractice.practice.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.javaPractice.practice.domain.Ebook;
import com.javaPractice.practice.domain.EbookExample;
import com.javaPractice.practice.mapper.EbookMapper;
import com.javaPractice.practice.req.EbookReq;
import com.javaPractice.practice.resp.EbookResp;
import com.javaPractice.practice.util.CopyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;


//    public List<Ebook> list(EbookReq req) {
//        return ebookMapper.selectByExample(null);
//    }

    public List<EbookResp> list(EbookReq req) {
//        EbookReq req --请求参数的封装
//        以下两句写法固定--Example,一般用来组装where条件和order by
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();  // criteria相当于where条件
        criteria.andNameLike("%" + req.getName() + "%");
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

//        不提取BeanUtils
//        List<EbookResp> respList = new ArrayList<>();
//        for (Ebook ebook : ebookList) {
////            不用工具类写法-一个个set
////            ebookResp.setId(ebook.getId());
//
////            工具类-copyProperties
//              EbookResp ebookResp = new EbookResp();
//            BeanUtils.copyProperties(ebook, ebookResp);
//            对象复制-以上两行可用自己写的copy
//            EbookResp ebookResp = ebookResp.copy(ebook, EbookResp);
//            ebookResp.setCategory1Id(123L);
//            respList.add(ebookResp);
//        }
        // 返回自己想要的，copyList--copy列表
        List<EbookResp> respList = CopyUtil.copyList(ebookList, EbookResp.class);
        System.out.println(11);
        return respList;
    }
}
