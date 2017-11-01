package com.yingu.relationengine.remote.feign;

import com.yingu.relationengine.client.AuthorizedFeignClient;
import com.yingu.relationengine.config.FeignSkipBadRequestsConfiguration;
import com.yingu.relationengine.swagger.model.SuccessModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@AuthorizedFeignClient(name = "ruleengine", configuration = FeignSkipBadRequestsConfiguration.class)
public interface RuleEngineFeignClient {

    @RequestMapping(value = "/foreign/custom/{globalId}/{appKey}/{appSecret}",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<SuccessModel> getCustomInfo(@ApiParam(value = "globalId of custom that needs to be fetched", required = true) @PathVariable("globalId") String globalId, @ApiParam(value = "应用key,向服务商索要", required = true) @PathVariable("appKey") String appKey, @ApiParam(value = "应用appSecret,向服务商索要", required = true) @PathVariable("appSecret") String appSecret);



}
