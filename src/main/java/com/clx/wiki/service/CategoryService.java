package com.clx.wiki.service;

import com.clx.wiki.domain.Category;
import com.clx.wiki.domain.CategoryExample;
import com.clx.wiki.mapper.CategoryMapper;
import com.clx.wiki.req.CategoryQueryReq;
import com.clx.wiki.req.CategorySaveReq;
import com.clx.wiki.resp.CategoryQueryResp;
import com.clx.wiki.resp.PageResp;
import com.clx.wiki.util.CopyUtil;
import com.clx.wiki.util.SnowFlake;
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
public class CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);


    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<CategoryQueryResp> list(CategoryQueryReq req){
        PageHelper.startPage(1,3);
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());
//        List<CategoryResp> respList = new ArrayList<>();
//        for (Category category : categoryList) {
//            //CategoryResp categoryResp = new CategoryResp();
//            //BeanUtils.copyProperties(category,categoryResp);
//
//            //对象复制
//            CategoryResp categoryResp = CopyUtil.copy(category, CategoryResp.class);
//
//            respList.add(categoryResp);
//        }



        //列表复制
        List<CategoryQueryResp> list = CopyUtil.copyList(categoryList, CategoryQueryResp.class);

        PageResp<CategoryQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    /**
     *  保存
     */
    public void save(CategorySaveReq req){
        Category category = CopyUtil.copy(req,Category.class);
        if (ObjectUtils.isEmpty(req.getId())){
            //新增
            category.setId(snowFlake.nextId());
            categoryMapper.insert(category);
        }else {
            //更新
            categoryMapper.updateByPrimaryKey(category);
        }

    }

    public void delete(Long id) {
        categoryMapper.deleteByPrimaryKey(id);
    }


}
