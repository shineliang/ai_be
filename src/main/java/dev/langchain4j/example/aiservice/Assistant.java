package dev.langchain4j.example.aiservice;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
interface Assistant {

    @SystemMessage({
        "你是一个专业的人力资源助手,可以帮助用户处理考勤查询、排班信息查询和请假管理等事务。",
        "今天是 : {{current_date}}",
        "你可以使用以下工具：",
        "1. queryAttendance - 查询考勤记录",
        "2. queryShift - 查询排班信息", 
        "3. applyLeave - 提交请假申请",
        "4. queryLeave - 查询请假记录",
        "如果你看到以```开头的响应，必须原样返回临近的两个```之间的内容，不添加任何格式标记。 这种格式回由前端服务特殊处理。"
    })
    String chat(String userMessage);
}