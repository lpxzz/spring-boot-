package com.example.demo.controller;

import com.example.demo.domain.Result;
import com.example.demo.service.UserService;
import com.example.demo.util.ResultUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserActionController {

    @Autowired
    private UserService userService;

    @Autowired
    RabbitTemplate rabbitTemplate;


    @GetMapping(value = "/login")
    public String userLogin(){
        String jsonWebToken = Jwts.builder().setSubject("testUser").claim("roles", "member")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretKey").compact();
        return jsonWebToken;
    }

    @GetMapping(value = "/userRabbit/{value}")
    public void userRabbit(@PathVariable String value){
        rabbitTemplate.convertAndSend("userQueue",value);
        System.out.println("value = [" + value + "]");
    }

    /**
     * 访问此方法需要鉴权
     * @param id
     * @return
     */
    @GetMapping(value = "/secure/findUser/{id}")
    public Result findUser(@PathVariable Long id) {
        return ResultUtil.success(userService.findUser(id));
    }
}
