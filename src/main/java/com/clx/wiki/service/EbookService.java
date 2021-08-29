package com.clx.wiki.service;

import com.clx.wiki.domain.Ebook;
import com.clx.wiki.domain.EbookExample;
import com.clx.wiki.mapper.EbookMapper;
import com.clx.wiki.req.EbookReq;
import com.clx.wiki.resp.EbookResp;
import com.clx.wiki.util.CopyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author clx
 * @create 2021/8/29 - 18:11
 */
@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();

        criteria.andNameLike("%" + req.getName() + "%");

        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

//        List<EbookResp> respList = new ArrayList<>();
//        for (Ebook ebook : ebookList) {
//            //EbookResp ebookResp = new EbookResp();
//            //BeanUtils.copyProperties(ebook,ebookResp);
//
//            //对象复制
//            EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
//
//            respList.add(ebookResp);
//        }

        //列表复制
        List<EbookResp> list = CopyUtil.copyList(ebookList, EbookResp.class);

        return list;
    }

}
