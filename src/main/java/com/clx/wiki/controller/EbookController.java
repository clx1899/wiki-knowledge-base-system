package com.clx.wiki.controller;

import com.clx.wiki.req.EbookReq;
import com.clx.wiki.resp.CommonResp;
import com.clx.wiki.resp.EbookResp;
import com.clx.wiki.resp.PageResp;
import com.clx.wiki.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author clx
 * @create 2021/8/29 - 14:44
 */
@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookReq req){
        CommonResp<PageResp<EbookResp>> resp = new CommonResp<>();
        PageResp<EbookResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }

}
