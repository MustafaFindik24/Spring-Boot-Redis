## Redis Nedir?

Redis (Remote Dictionary Server), açık kaynaklı noSQL tabanlı bir veritabanı yapısıdır. Birden fazla kullanım çeşidi olmakla birlikte kullanılmasının başlıca sebeplerinden birisi bellek (in-memory) içerisinde çalıştığı için request - response süresini azaltmaktır. Veri cache bellekte tutulduğundan dolayı bu sayede uygulamaların daha hızlı şekilde yüklenmesi, görselleştirilmesi ve uygulamaya dinamik bir çerçeve kazandırılması amaçlanır.

![redis](https://user-images.githubusercontent.com/91599453/224338938-6a49c685-2d54-451f-821a-6ebe8c9fd410.png)

## Spring Boot uygulamasında Redis kullanımı

# 🎯 REGEX SERVICE ?

```java
public class RegexUtils {
    public String usernameRegex(String username) {
        String regex = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
    }
}
