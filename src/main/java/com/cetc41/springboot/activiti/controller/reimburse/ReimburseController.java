package com.cetc41.springboot.activiti.controller.reimburse;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther liupeiqing
 * @date 2019/6/14 11:13
 */
@RestController
public class ReimburseController {

    /**
     * 2：启动流程实例
     * 可以在启动流程时把所有流程变量设置好
     */
    @GetMapping("/startReimburseProcess")
    public void reimburseStartProcessInstance(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getRuntimeService()
                .startProcessInstanceById("reimburseProcess:1:30004");  //这个是查看数据库中act_re_procdef表
        //启动完成后，会走向第一个流程节点，在act_ru_task会有第一个节点的数据
    }

    /**
     * 3.流到审批，
     * 可以在执行任务时候设置流程变量
     */
    @GetMapping("/completeFirst")
    public void completeFirst(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        //Task task = getCurrentTask();
        String taskId = "32504";
        Map<String,Object> map = new HashMap<String,Object>() ;
        map.put("money",2000);
        taskService.complete(taskId,map);

        System.out.println("审批完毕");
    }

    /**
     * 3.流到审批，
     * 可以在执行任务时候设置流程变量
     */
    @GetMapping("/completeNext")
    public void completeNext(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        String taskId = "35005";
        taskService.complete(taskId);
        System.out.println("审批完毕");

    }

    @GetMapping("/getAllTask")
    public List<Task> getAllTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().list();
        return tasks;
    }
}
