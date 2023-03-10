## Redis Nedir?

Redis (Remote Dictionary Server), açık kaynaklı noSQL tabanlı bir veritabanı yapısıdır. Birden fazla kullanım çeşidi olmakla birlikte kullanılmasının başlıca sebeplerinden birisi bellek (in-memory) içerisinde çalıştığı için request - response süresini azaltmaktır. Veri cache bellekte tutulduğundan dolayı bu sayede uygulamaların daha hızlı şekilde yüklenmesi, görselleştirilmesi ve uygulamaya dinamik bir çerçeve kazandırılması amaçlanır.

![redis](https://user-images.githubusercontent.com/91599453/224338938-6a49c685-2d54-451f-821a-6ebe8c9fd410.png)

# 🎯 Spring Boot uygulamasında Redis kullanımı

* Spring Boot projesine maven kullanarak pom.xml dosyasına redis kullanımı için dependency eklemesi gerçekleştirildi.

```xml
        <dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-redis</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
	<dependency>
		<groupId>redis.clients</groupId>
		<artifactId>jedis</artifactId>
	</dependency>
```

* Docker compose dosyası oluşturularak redis kullanımı için container ayağa kaldırıldı.

```yml
version: "3.5"
services:
  redis:
    image: redis:latest
    ports:
      - "6379:6379"
```

* Configuration dosyası oluşturularak redisConnectionFactory() ve redisTemplate() Spring IOC container içerisine eklenmesi için bean anotasyonu ile belirtildi.

```java
@Configuration
@EnableCaching
public class AppConfiguration {
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost",6379);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }
    @Bean
    public RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}
```

* Service sınıfı oluşturularak @Cacheable ve @CacheEvict anotasyonları kullanılarak cache yönetimi ve cache temizlenmesi incelendi.

```java
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
```

* Controller sınıfında localhost:8080/test pathi üzerinden erişilecek bir metot yazıldı.

```java
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
```
