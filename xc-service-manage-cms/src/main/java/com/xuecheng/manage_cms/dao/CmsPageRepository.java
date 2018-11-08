package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsPageRepository extends MongoRepository<CmsPage,String> {
    //根据页面名称查询
    CmsPage findByPageName(String pageName);
    //根据页面名称和类型查询
        CmsPage findByPageNameAndPageType(String pageName,String pageType);
    //统计根据。。。查询的记录数
    int countByPageNameAndPageType(String pageName,String pageType);
    //根据页面站点名称和类型查询
    CmsPage findBySiteIdAndPageType(String siteName,String pageType);
    //统计根据页面站点名称和类型查询
    int countBySiteIdAndPageType(String siteName,String pageType);
    //根据页面名称、站点id、页面webpath查询页面
    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName,String siteId,String pageWebPath);
}
