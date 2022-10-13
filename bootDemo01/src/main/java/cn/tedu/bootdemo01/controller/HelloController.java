package cn.tedu.bootdemo01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yaohao
 */
@Controller
public class BMIController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "服务器接收到了响应请求2222";
    }
}
