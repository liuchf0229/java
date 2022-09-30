package com.javaPractice.practice.controller;

import com.javaPractice.practice.domain.Ebook;
import com.javaPractice.practice.resp.CommonResp;
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
    public CommonResp list() {
        CommonResp<List<Ebook>> resp = new CommonResp<>();
        List<Ebook> list = EbookService.list();
        resp.setContent(list);
        return resp;
    }
}
