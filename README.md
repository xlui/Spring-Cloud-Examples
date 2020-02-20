# Spring-Cloud-Examples

Some examples for using spring cloud.

## Registry

First, we need a registry to manage and discover services. We choose [Spring Cloud Eureka](https://cloud.spring.io/spring-cloud-netflix/reference/html/#spring-cloud-eureka-server).

To set up a Eureka server, we need do the following things:

1. enable eureka server on spring configuration class

```java
@EnableEurekaServer
@SpringBootApplication
public class HelloApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}
}
```

2. config the registry(here we use Standalone Mode):

```conf
server.port=9000
eureka.instance.hostname=localhost
# don't register self
eureka.client.register-with-eureka=false
# don't fetch services
eureka.client.fetch-registry=false
eureka.client.service-url.default-zone=http://${eureka.instance.hostname}:${server.port}/eureka
```

Also, we can make Eureka more resilient and available by running multiple instances and asking them to register to each other. Follow the [docs](https://cloud.spring.io/spring-cloud-netflix/reference/html/#spring-cloud-eureka-server-peer-awareness).

## Feign

Feign provides a simple and easy way to make REST based RPC through services.

### Feign Server

A Feign Server is nothings special than a normal spring mvc server. We just need to declare it as a REST controller, and register our service to the registry.

### Feign Client

Follow the [docs](https://cloud.spring.io/spring-cloud-openfeign/reference/html/#netflix-feign-starter).

1. First, enable feign client and eureka discovery

```java
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }
}
```

2. Second, declare server's endpoints in client

```java
@FeignClient("feign-server")
public interface HelloService {
    @RequestMapping("/helloserver")
    String helloServer();

    @RequestMapping("/testRawParam")
    String testRawParam(@RequestParam(value = "p1") Integer p1, @RequestBody Hashtable p2);
}
```

3. And then, call server through `HelloService` in client's controller or service

```java
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
```

4. At last, don't forget to register client to our registry

```conf
spring.application.name=feign-client
server.port=9002
# registry
eureka.client.service-url.defaultZone=http://localhost:9000/eureka
```

You can test client by visit:

> http://localhost:9002/hello
> http://localhost:9002/testRawParam

## LICENSE

MIT
