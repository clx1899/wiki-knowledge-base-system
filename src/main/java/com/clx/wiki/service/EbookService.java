package com.clx.wiki.service;

import com.clx.wiki.domain.Ebook;
import com.clx.wiki.mapper.EbookMapper;
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

    public List<Ebook> list(){
        return ebookMapper.selectByExample(null);
    }

}
