package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * (Payment)表控制层
 *
 * @author makejava
 * @since 2020-03-06 20:24:57
 */
@RestController
@Slf4j
public class PaymentController {
    /**
     * 服务对象
     */
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;


    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("******插入结果" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据成功:" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败:" + serverPort);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("******查询结果" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功:" + serverPort, payment);
        } else {
            return new CommonResult(444, "没有对应记录:" + serverPort + ",查询ID：" + id);
        }
    }
}