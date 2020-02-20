package app.xlui.example.spring.cloud.hello.feign.server;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Hashtable;

@RestController
public class FeignServerController {
    @RequestMapping(value = "/helloserver", method = RequestMethod.GET)
    public String helloServer() {
        return "I'am a feign server.";
    }

    @RequestMapping(value = "/testRawParam")
    public String testRawParam(Integer p1, @RequestBody Hashtable p2, HttpServletRequest request) {
        System.out.println("request method:" + request.getMethod());
        return "" + p1 + p2.toString();
    }
}
