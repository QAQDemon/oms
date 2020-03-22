package com.neu.edu.oms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Api(tags={"用户操作接口"})
@RestController
public class TestController {

    /**
     * 设置数据，返回到freemarker视图
     * @return
     */
    @ApiOperation(value = "登录", notes = "账号密码必输")
    @RequestMapping(value={"/test/say"},method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String say(@ApiParam(required = true,name="json",value="json1") @RequestBody String json){
        return "{msg:1}";
    }
}