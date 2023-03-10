## Redis Nedir?

Redis (Remote Dictionary Server), açık kaynaklı noSQL tabanlı bir veritabanı yapısıdır. Birden fazla kullanım çeşidi olmakla birlikte kullanılmasının başlıca sebeplerinden birisi bellek (in-memory) içerisinde çalıştığı için request - response süresini azaltmaktır. Veri cache bellekte tutulduğundan dolayı bu sayede uygulamaların daha hızlı şekilde yüklenmesi, görselleştirilmesi ve uygulamaya dinamik bir çerçeve kazandırılması amaçlanır.

![redis](https://user-images.githubusercontent.com/91599453/224338938-6a49c685-2d54-451f-821a-6ebe8c9fd410.png)

# 🎯 Spring Boot uygulamasında Redis kullanımı


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
