package com.mustafafindik.cacheapp.Controller;

import com.mustafafindik.cacheapp.Service.RedisCacheService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisCacheController {

    private final RedisCacheService redisCacheService;
    int counter = 0;

    public RedisCacheController(RedisCacheService redisCacheService) {
        this.redisCacheService = redisCacheService;
    }
    @GetMapping
    public String cacheCheck()throws Exception{
        if (counter == 3){
            redisCacheService.clearCaching();
            counter=0;
        }
        counter++;
        return redisCacheService.runningMethod();
    }
}
