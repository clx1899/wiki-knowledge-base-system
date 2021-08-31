package com.clx.wiki.service;

import com.clx.wiki.domain.Ebook;
import com.clx.wiki.domain.EbookExample;
import com.clx.wiki.mapper.EbookMapper;
import com.clx.wiki.req.EbookQueryReq;
import com.clx.wiki.req.EbookSaveReq;
import com.clx.wiki.resp.EbookQueryResp;
import com.clx.wiki.resp.PageResp;
import com.clx.wiki.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author clx
 * @create 2021/8/29 - 18:11
 */
@Service
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);


    @Resource
    private EbookMapper ebookMapper;

    public PageResp<EbookQueryResp> list(EbookQueryReq req){
        PageHelper.startPage(1,3);
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%" + req.getName() + "%");

        }
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());
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
        List<EbookQueryResp> list = CopyUtil.copyList(ebookList, EbookQueryResp.class);

        PageResp<EbookQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    /**
     *
     */
    public void save(EbookSaveReq req){
        Ebook ebook = CopyUtil.copy(req,Ebook.class);
        if (ObjectUtils.isEmpty(req.getId())){
            //新增
            ebookMapper.insert(ebook);
        }else {
            //更新
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }

}
