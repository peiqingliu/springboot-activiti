# springboot-activiti
springboot整合activiti在线设计器
页面打开：http://localhost:8082/modeler.html?modelId=1

25张表详解：

1、act_ge_ 通用数据表，ge是general的缩写

2、act_hi_ 历史数据表，hi是history的缩写，对应HistoryService接口

3、act_id_ 身份数据表，id是identity的缩写，对应IdentityService接口

4、act_re_ 流程存储表，re是repository的缩写，对应RepositoryService接口，存储流程部署和流程定义等静态数据

5、act_ru_ 运行时数据表，ru是runtime的缩写，对应RuntimeService接口和TaskService接口，存储流程实例和用户任务等动态数

与部署对象和流程定义相关的表

act_re_model: 流程模型表，绘制完流程之后，保存在该表。

act_re_procdef: 流程定义表，如果发布部署的文件是流程文件，除了将内容保存到资源表外，还会解析流程文件的内容，形成特定的流程定义数据，保存到此表中。

act_re_deployment 部署数据表，一次部署可以添加多个资源，资源会被保存到资源表（act_ge_bytearray）中；而部署的信息，则保存到部署表中

资源相关表

act_ge_bytearray：资源表，用来保存资源相应的信息

act_ge_property：用来生成下一个主键信息。

与流程实例，执行对象，任务相关的表

act_ru_execution：正在执行的流程实例表，当流程启动后，会产生一个流程实例，同时会产生相应的执行流，那么流程实例和执行流数据均会被保存。

act_ru_execution表中。

act_hi_procinst：流程实例的历时表，与act_ru_execution正好对应。

act_ru_task：正在执行的任务列表（只有任务节点（UserTask），该表中才有数据）。

act_hi_actinst：所有活动的历史表（包括所有的节点）

与流程变量相关的表

act_ru_variable： 正在执行的流程变量表，用来保存在整个流程执行过程中用到的变量信息

act_hi_varinst ：历史的流程变量表，与act_ru_variable正好对应

与组任务表相关的表

act_ru_identitylink ：正在执行的组任务表

act_hi_identitylink：历史的人员表

与组织结构相关的表

act_id_group ：工作流中的角色表

act_id_user ：工作流中的用户表

act_id_membership：中间表，关联关系表
