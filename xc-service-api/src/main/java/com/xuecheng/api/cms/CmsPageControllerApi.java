package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@Api(value = "cms页面查询管理接口",description = "cms页面管理接口，负责页面的增删改查")
public interface CmsPageControllerApi {
    //页面查询
    @ApiOperation("分页查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",required = true,paramType = "path",dataType = "int"),
            @ApiImplicitParam(name = "size" ,value = "每页大小",required = true,paramType = "path",dataType = "int"),
    })
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    @ApiOperation("添加页面")
    @ApiImplicitParam(name = "cmsPage" , value = "页面容器")
    public CmsPageResult add(CmsPage cmsPage);

    @ApiOperation("根据Id查询")
    @ApiImplicitParam(name = "id" , value = "主键ID")
    public CmsPage findById(String id);

    @ApiOperation("修改页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id" ,value = "主键ID"),
            @ApiImplicitParam(name = "cmsPage" ,value = "页面容器")
    })
    public CmsPageResult edit(String id, CmsPage cmsPage);
}
