package com.javaPractice.practice.controller;

import com.javaPractice.practice.domain.Ebook;
import com.javaPractice.practice.req.EbookReq;
import com.javaPractice.practice.resp.CommonResp;
import com.javaPractice.practice.resp.EbookResp;
import com.javaPractice.practice.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService EbookService;

    @GetMapping("/list")
//    public List<Ebook> list() {
//        return EbookService.list();
//    }
    public CommonResp list(EbookReq req) {
        CommonResp<List<EbookResp>> resp = new CommonResp<>();
        List<EbookResp> list = EbookService.list(req);
        resp.setContent(list);
        return resp;
    }
}
