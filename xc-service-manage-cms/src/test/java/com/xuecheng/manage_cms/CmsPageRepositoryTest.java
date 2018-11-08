package com.xuecheng.manage_cms;


import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {

    @Autowired
    CmsPageRepository cmsPageRepository;


    //自定义条件查询测试
    @Test
    public void testConditionQuery(){

        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        exampleMatcher = exampleMatcher.withMatcher("pageAliase",ExampleMatcher.GenericPropertyMatchers.startsWith());
//页面别名模糊查询，需要自定义字符串的匹配器实现模糊查询
        //条件值
        CmsPage cmsPage = new CmsPage();
        //站点ID
      //  cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
        //模板ID
      //  cmsPage.setTemplateId("5a962c16b00ffc514038fafd");
        //页面别名
        cmsPage.setPageAliase("轮播图");
        //创建条件实例
        Example<CmsPage> cmsPageExample = Example.of(cmsPage,exampleMatcher);
        //分页查询
        Pageable pageable = new PageRequest(0,10);

        Page<CmsPage> all = cmsPageRepository.findAll(cmsPageExample,pageable);

        System.out.println("all"+all);

    }




    //根据页面名称查询
    @Test
    public void testFindByPageName(){
     CmsPage  cmsPage=  cmsPageRepository.findByPageName("cforeach的自定义页面");
        System.out.println(cmsPage);
    }

    //测试根据pageName和pageType
    @Test
    public void testFindByPageNameAndPageType(){
       CmsPage cmsPage = cmsPageRepository.findByPageNameAndPageType("cforeach的自定义页面","1");
        System.out.println(cmsPage);
    }

    //countBy..name,type
    @Test
    public void testCountFindByPageNameAndPageType(){
         int result = cmsPageRepository.countByPageNameAndPageType("cforeach的自定义页面", "1");
        System.out.println(result);
    }
    //findBy sitid and typeName
    @Test
    public void testFindBySiteIdAndTypeName(){
        CmsPage bySiteIdAndPageType = cmsPageRepository.findBySiteIdAndPageType("测试sitID", "1");
        System.out.println(bySiteIdAndPageType);
    }
    //count find by siteId and typeName
    @Test
    public  void testCountFindBySiteIdAndTypeName(){
        int resultCount = cmsPageRepository.countBySiteIdAndPageType("测试sitID", "1");
        System.out.println("resultCount::"+resultCount);
    }






/*@Test
public  void testFindAll(){
    Pageable pageable = PageRequest.of(0,10);
   Page<CmsPage> all = cmsPageRepository.findAll(pageable);
   for (CmsPage each : all){
       System.out.println(each);
   }


}*/

    // update
   /* @Test
    public void testUpdate() {
        Optional<CmsPage> cmsPageOptional = cmsPageRepository.findById("5bc9b598981e58627c3c3438");
        if (cmsPageOptional != null) {
            CmsPage cmsPage = new CmsPage();
            cmsPage.setPageName("this is new name");
            cmsPage.setSiteId("WHOS YOUR DADDY");
            cmsPageRepository.save(cmsPage);
        }
    }*/


//delete test
    /*@Test
    public void testDelete(){
  cmsPageRepository.deleteById("5bc9ab77981e5899e810b3ad");

    }*/


    //test insert
    //@Test
    /*public void testInsert() {
        CmsPage cmsPage = new CmsPage();
cmsPage.setPageName("cforeach的自定义页面");
cmsPage.setSiteId("测试sitID");
cmsPage.setTemplateId("测试模板id");
cmsPage.setPageCreateTime(new Date());
        List<CmsPageParam> list = new ArrayList<>();
        CmsPageParam cmsPageParam = new CmsPageParam();
        cmsPageParam.setPageParamName("自定义param测试");
        cmsPageParam.setPageParamValue("自定义paramValue测试");
        list.add(cmsPageParam);
        cmsPage.setPageParams(list);
       cmsPageRepository.save(cmsPage);
       System.out.println(cmsPage);

    }*/
//测试连接mongodb

    // @Test
   /* public void testConnection(){
        //MongoClient mongoClient = new MongoClient("localhost",27017);
        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://cforeach:123@localhost:27017");
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase database = mongoClient.getDatabase("test");
MongoCollection<org.bson.Document> collection = database.getCollection("student");
Document myDoc = collection.find().first();
String json =  myDoc.toJson();
System.out.println(json);};*/


}
