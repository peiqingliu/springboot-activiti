package com.cetc41.springboot.activiti.controller.contract;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 合同流程
 * @auther liupeiqing
 * @date 2019/6/14 14:59
 */
@RestController
public class ContractController {

    /**
     * 2：启动流程实例
     * 可以在启动流程时把所有流程变量设置好
     */
    @GetMapping("/startContract")
    public void contractStartProcessInstance(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //设置流程变量applyUserId=liupeiqing
        //第一个流程里面就有人
        paramMap.put("leaderId", "张飞");

        processEngine.getRuntimeService()
                .startProcessInstanceById("contract:1:72512",paramMap);  //这个是查看数据库中act_re_procdef表

        //启动完成后，会走向第一个流程节点，在act_ru_task会有第一个节点的数据
    }

    @GetMapping("/completeApproval")
    public void completeFirst(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        String taskId = "75006";
        //设置下一个流程审批人员
        Map<String,Object> map = new HashMap<String,Object>() ;
        map.put("money",20000);
        map.put("director","刘备");
        taskService.complete(taskId,map);
        System.out.println("部门领导审批完毕");
    }

    @GetMapping("/completeDirector")
    public String completeLeader(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        String taskId = "77507";
        //设置下一个流程审批人员
        Map<String,Object> map = new HashMap<String,Object>() ;
        map.put("message",1);
        map.put("President","曹操");
        taskService.complete(taskId,map);

        System.out.println("主任审批完毕，不同意");
        return "审批完毕";
    }

    @GetMapping("/completePresident")
    public String completePresident(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        String taskId = "82507";
        //设置下一个流程审批人员
        Map<String,Object> map = new HashMap<String,Object>() ;
        taskService.complete(taskId);

        System.out.println("董事长审批完毕");
        return "审批完毕";
    }


}
