package app.xlui.example.spring.cloud.hello.feign.client;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Hashtable;

@RestController
public class FeignClientController {
    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello")
    public String hello() {
        return helloService.helloServer();
    }

    @RequestMapping("/testRawParam")
    public String testRawParam() {
        val hashtable = new Hashtable<String, String>();
        hashtable.put("1", "1");
        hashtable.put("2", "11");
        hashtable.put("3", "111");
        return helloService.testRawParam(1, hashtable);
    }
}
