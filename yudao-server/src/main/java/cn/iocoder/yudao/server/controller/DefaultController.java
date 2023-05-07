package cn.iocoder.yudao.server.controller;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.pay.controller.admin.demo.vo.PayDemoOrderCreateReqVO;
import cn.iocoder.yudao.module.pay.controller.admin.order.vo.PayOrderSubmitReqVO;
import cn.iocoder.yudao.module.pay.controller.admin.order.vo.PayOrderSubmitRespVO;
import cn.iocoder.yudao.module.pay.service.demo.PayDemoOrderService;
import cn.iocoder.yudao.module.pay.service.order.PayOrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.NOT_IMPLEMENTED;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.servlet.ServletUtils.getClientIP;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

/**
 * 默认 Controller，解决部分 module 未开启时的 404 提示。
 * 例如说，/bpm/** 路径，工作流
 *
 * @author 芋道源码
 */
@RestController
public class DefaultController {
    @Resource
    private PayOrderService payOrderService;
    @Resource
    private PayDemoOrderService payDemoOrderService;

    @PostMapping("/app-api/bpm1/create")
    @Operation(summary = "创建示例订单")
    public CommonResult<Long> createDemoOrder(@Valid @RequestBody PayDemoOrderCreateReqVO createReqVO) {
        return success(payDemoOrderService.createDemoOrder(getLoginUserId(), createReqVO));
    }

    @PostMapping("/app-api/bpm1/submit")
    @Operation(summary = "提交支付订单")
    public CommonResult<PayOrderSubmitRespVO> submitPayOrder(@RequestBody PayOrderSubmitReqVO reqVO) {

        PayOrderSubmitRespVO respVO = payOrderService.submitPayOrder(reqVO, getClientIP());
        return success(respVO);
    }



    @RequestMapping("/admin-api/bpm/**")
    public CommonResult<Boolean> bpm404() {
        return CommonResult.error(NOT_IMPLEMENTED.getCode(),
                "[工作流模块 yudao-module-bpm - 已禁用][参考 https://doc.iocoder.cn/bpm/ 开启]");
    }

    @RequestMapping("/admin-api/mp/**")
    public CommonResult<Boolean> mp404() {
        return CommonResult.error(NOT_IMPLEMENTED.getCode(),
                "[微信公众号 yudao-module-mp - 已禁用][参考 https://doc.iocoder.cn/mp/build/ 开启]");
    }

    @RequestMapping(value = {"/admin-api/product/**", // 商品中心
            "/admin-api/trade/**", // 交易中心
            "/admin-api/promotion/**"})  // 营销中心
    public CommonResult<Boolean> mall404() {
        return CommonResult.error(NOT_IMPLEMENTED.getCode(),
                "[商城系统 yudao-module-mall - 已禁用][参考 https://doc.iocoder.cn/mall/build/ 开启]");
    }

    @RequestMapping(value = {"/admin-api/report/**"})
    public CommonResult<Boolean> report404() {
        return CommonResult.error(NOT_IMPLEMENTED.getCode(),
                "[报表模块 yudao-module-report - 已禁用][参考 https://doc.iocoder.cn/report/ 开启]");
    }

}
