package com.xuecheng.manage_cms.web.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.manage_cms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    PageService pageService;

    //根据id查询
    @Override
    @GetMapping("/get/{id}")
    public CmsPage findById(@PathVariable("id") String id) {
        System.out.println("findById function come in");
        CmsPage cmsPage = pageService.findById(id);
        return cmsPage;
    }

    //修改页面
    @Override
    @PutMapping("/edit/{id}")
    public CmsPageResult edit(@PathVariable("id") String id,@RequestBody CmsPage cmsPage) {
        System.out.println("edit function come in");
        return pageService.update(id,cmsPage);
    }

    //新增页面
    @Override
    @PostMapping("/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage){
        return pageService.add(cmsPage);
    }


    //查询所有
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int
            size, QueryPageRequest queryPageRequest) {
        //暂时采用测试数据，测试接口是否可以正常运行
       /* QueryResult<CmsPage> queryResult = new QueryResult<>();
        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageName("测试页面");
        List list = new ArrayList<>();
        list.add(cmsPage);
        queryResult.setList(list);
        queryResult.setTotal(2);*/
        //QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);

        return pageService.findList(page,size,queryPageRequest);
    }

}
