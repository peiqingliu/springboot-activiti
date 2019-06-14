package com.cetc41.springboot.activiti.controller.overrule;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 驳回测试
 * @auther liupeiqing
 * @date 2019/6/14 16:42
 */
@RestController
public class OverruleController {

    /**
     * 2：启动流程实例
     * 可以在启动流程时把所有流程变量设置好
     */
    @GetMapping("/startOverruleProcess")
    public void overruleStartProcessInstance(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //设置流程变量applyUserId=liupeiqing
        //第一个流程里面就有人
        paramMap.put("applyUserId", "赵云");
        processEngine.getRuntimeService()
                .startProcessInstanceById("overrule:1:85012",paramMap);  //这个是查看数据库中act_re_procdef表
    }

    /**
     * 3.流到审批，
     * 可以在执行任务时候设置流程变量
     */
    @GetMapping("/applyComplete")
    public void complete1(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        String taskId = "90006";
        Map<String,Object> map = new HashMap<String,Object>() ;
        map.put("leaderId","刘备");
        taskService.complete(taskId,map);
        System.out.println("部门领导审批完毕");
    }

    /**
     * 3.流到审批，
     * 可以在执行任务时候设置流程变量
     */
    @GetMapping("/leaderComplete")
    public void complete2(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        String taskId = "92504";
        Map<String,Object> map = new HashMap<String,Object>() ;
        map.put("day",10);
        taskService.complete(taskId,map);
        System.out.println("部门领导审批完毕,不同意,流到发起人");
    }

    /**
     * 3.流到审批，
     * 可以在执行任务时候设置流程变量
     */
    @GetMapping("/applyComplete2")
    public void complete3(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        String taskId = "95005";
        Map<String,Object> map = new HashMap<String,Object>() ;
        map.put("day",5);
        map.put("leaderId","刘备");
        taskService.complete(taskId,map);
        System.out.println("驳回之后，修改，重新审批");
    }

    /**
     * 4.流到 领导重新审批，
     * 可以在执行任务时候设置流程变量
     */
    @GetMapping("/applyComplete4")
    public void complete4(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        String taskId = "97504";
        Map<String,Object> map = new HashMap<String,Object>() ;
        map.put("humanAffairs","曹操");
        taskService.complete(taskId,map);
        System.out.println("部门同意之后。到人事部");
    }

    @GetMapping("/applyComplete5")
    public void complete5(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        String taskId = "100005";
        Map<String,Object> map = new HashMap<String,Object>() ;
        taskService.complete(taskId,map);
        System.out.println("人事部同意");
    }
}
