package app.xlui.example.spring.cloud.hello.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Hashtable;

@FeignClient("feign-server")
public interface HelloService {
    @RequestMapping("/helloserver")
    String helloServer();

    @RequestMapping("/testRawParam")
    String testRawParam(@RequestParam(value = "p1") Integer p1, @RequestBody Hashtable p2);
}