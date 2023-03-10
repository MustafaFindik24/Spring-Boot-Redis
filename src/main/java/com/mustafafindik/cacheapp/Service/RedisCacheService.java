package com.mustafafindik.cacheapp.Service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService {
    @Cacheable(cacheNames = "myCacheMethod")
    public String runningMethod() throws Exception{
        Thread.sleep(5000L);
        return "Metot çalıştırıldı.";
    }
    @CacheEvict(cacheNames = "myCacheMethod")
    public void clearCaching(){
        System.out.println("Cache temizlendi.");
    }

}
