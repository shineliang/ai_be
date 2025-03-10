package dev.langchain4j.example.service.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

@AiService
public interface HRAssistant {
    
    @SystemMessage({
        "你是一个专业的人力资源助手,可以帮助用户处理考勤查询、排班信息查询和请假管理等事务。",
        "今天是 : {{current_date}}",
        "你可以使用以下工具:",
        "1. queryAttendance - 查询考勤记录",
        "2. queryShift - 查询排班信息", 
        "3. applyLeave - 提交请假申请",
        "4. queryLeave - 查询请假记录",
        "If you are writing code, do not include the \"line_number|\" before each line of code."
    })
    Flux<String> chat(String userMessage);
} 