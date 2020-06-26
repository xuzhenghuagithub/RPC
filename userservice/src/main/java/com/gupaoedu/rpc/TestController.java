package com.gupaoedu.rpc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @MyReference
    IOrderService orderService;
    @MyReference
    ITestService testService;

    /**
     *http://localhost:8080/queryOrderList
     * @return
     */
    @GetMapping("queryOrderList")
    public String queryOrderList() {
        return orderService.queryOrderList();
    }

    @GetMapping("queryOrderById")
    public String queryOrderById() {
        return orderService.queryOrderById();
    }

    @GetMapping("test")
    public String test() {
        return testService.test();
    }
}
