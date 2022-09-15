package com.liuchf;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

//import javax.annotation.Resource;

@RestController
public class TestController {
//    @Resource
//    @Autowired
//    private GirlProperties girlProperties;

    @Value("${cupSize}")
    private String cupSize;

    /**
     * id：获取url中的参数
     * cupSize：获取配置文件中的参数
     * @PathVariable("id") /hello/id
     * @RequestParam("id") /hello?id=
     *
     * */
    @GetMapping(value = "/hello")
    public String hello(
        @RequestParam(value = "id", required = false, defaultValue = "0") Integer myId
    ) {
        return "hello world12---" + cupSize + "---ID:" + myId;
    }

    @PostMapping(value = {"/hi"})
    public String hi() {
        return "hi" + cupSize;
    }
}
