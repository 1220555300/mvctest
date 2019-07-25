package com.mvctest;

import com.entity.PageData;
import com.services.listService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.RequestWrapper;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
//@RequestMapping("/list")   //url/list/list
public class listController {
    @Autowired
    private listService service;

    @RequestMapping(value = "/list")
    public String list(){
        return "list";
    }

    @RequestMapping(value = "/plan_list",method = RequestMethod.POST)
    @ResponseBody
    public PageData listPlan(@RequestParam(value = "name",required = false) String name,
                             @RequestParam(value = "page",required = true,defaultValue = "1") Integer page,
                             @RequestParam(value = "size",required = true,defaultValue = "30") Integer size,
                             @RequestParam(value = "onlyInTime",required = false,defaultValue = "false") Boolean onlyInTime
    ){
        return service.findPlanBy(name, null,page,size,onlyInTime,null);
    }

    @RequestMapping(value = "/plan_add",method = RequestMethod.POST)
    @ResponseBody
    public boolean plan_add(String title,String begTime,String endTime,String refModelId,Boolean onlyVisibleToCreator,Boolean isAllowMultiSub) throws Exception{
       Map<String,Object> paramMap = new HashMap<String, Object>();
       paramMap.put("id", UUID.randomUUID().toString());
       paramMap.put("title",title);
       paramMap.put("status","未发布");
       paramMap.put("allow_multi", isAllowMultiSub);
       paramMap.put("only_visible_to_creator", onlyVisibleToCreator);
       SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
       if(begTime.contains("/")){
           begTime = begTime.replaceAll("/","-");
       }
       if(endTime.contains("/")){
           endTime = endTime.replaceAll("/","-");
       }
       paramMap.put("beg_time",sdf.parseObject(begTime));
       paramMap.put("end_time",sdf.parseObject(endTime));
       paramMap.put("ref_model_id", refModelId);
       paramMap.put("cteate_time",new Date());
       paramMap.put("create_name", "12231212");
       paramMap.put("creatorid", "24564456");
       service.add("survey_plan",paramMap,null);
       return true;
    }


//    public void plan_add(HttpServletRequest request){
//        System.out.println(request.getParameter("title"));
//        Map<String,String[]> map = request.getParameterMap();
//        Set<String> keySet = map.keySet();
//        Iterator<String> it = keySet.iterator();
//        while(it.hasNext()) {
//            String key = it.next();
//            String[] a=map.get(key);
//            for(int i=0;i<a.length;i++){
//                System.out.println(a[i]);
//            }
//        };
//    }
    @RequestMapping(value = "/del_plan",method = RequestMethod.GET)
    @ResponseBody
    public void delPlan(String id){
        if(service.isPlanCanModify(id)){
            service.delete("survey_plan",id);

        }
    }



    @RequestMapping(value = "up_plan",method = RequestMethod.POST)
    @ResponseBody
    public void  up_plan(String id,String title,String status, String begTime,String endTime) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        service.updatePlan(id,title,status,sdf.parse(begTime),sdf.parse(endTime));
    }

}
