package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PageService {
    @Autowired
    CmsPageRepository cmsPageRepository;

    //根据Id查询页面
    public CmsPage findById(String id){

        Optional<CmsPage> byId = cmsPageRepository.findById(id);
        boolean idPresent = byId.isPresent();
        if (idPresent) {
            return byId.get(); //返回找到的数据
        }
            return null;
    };

    //修改页面

    public CmsPageResult update(String id,CmsPage cmsPage){

        Optional<CmsPage> byId = cmsPageRepository.findById(id);

        boolean present = byId.isPresent();

        //如果数据库有对应的记录，执行更新
        if (present) {

            CmsPage repCmsPage = new CmsPage();
            //复制数据

            //设置id
            repCmsPage.setPageId(id);
            //别名
            repCmsPage.setPageAliase(cmsPage.getPageAliase());
            //sitId
            repCmsPage.setSiteId(cmsPage.getSiteId());
            //tempId
            repCmsPage.setTemplateId(cmsPage.getTemplateId());
            //
            repCmsPage.setPageName(cmsPage.getPageName());
            //物理路径
            repCmsPage.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            // url
            repCmsPage.setDataUrl(cmsPage.getDataUrl());

            //dao
            CmsPage insert = cmsPageRepository.insert(repCmsPage);

            //判断插入成功失败

            if (insert!=null){
                return new CmsPageResult(CommonCode.SUCCESS,insert);
            }
        }
        return new CmsPageResult(CommonCode.FAIL,null);
    };

    //添加页面
    public CmsPageResult add(CmsPage cmsPage) {
        //校验页面是否存在
        CmsPage queryResult = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if (queryResult == null) {
        //证明页面不存在
            cmsPage.setPageId(null);//添加页面主键，由springdata自动生成
            cmsPageRepository.save(cmsPage);
            //返回结果
            CmsPageResult cmsPageResult = new CmsPageResult(CommonCode.SUCCESS,cmsPage);

            return cmsPageResult;
        }
        return new CmsPageResult(CommonCode.FAIL,null);
    }


    //查询所有
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {
        if (queryPageRequest == null) {
            queryPageRequest = new QueryPageRequest();
        }
        //自定义条件查询
        //定义条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        //条件值对象
        CmsPage cmsPage = new CmsPage();
        //设置条件值（站点id）
        if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())) {
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        //设置模板id作为查询条件
        if (StringUtils.isNotEmpty(queryPageRequest.getTemplateId())) {
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        //设置页面别名作为查询条件
        if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())) {
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        //定义条件对象Example
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        //分页参数
        if (page <= 0) {
            page = 1;
        }
        page = page - 1;
        if (size <= 0) {
            size = 10;
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);//实现自定义条件查询并且分页查询
        QueryResult queryResult = new QueryResult();
        queryResult.setList(all.getContent());//数据列表
        queryResult.setTotal(all.getTotalElements());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }
}
