package designpatterns.creational;

import java.util.UUID;

/*
Factory Design Pattern, nesne oluşturma işlemini bir fabrikaya devreder ve istemcinin
hangi alt sınıfın nesnesinin oluşturulacağı konusunda endişelenmesine gerek kalmaz.
Bu desen, nesne oluşturma işlemini soyutlamak ve dinamik bir yapı sağlamak için kullanılır.
 */

// Coupon arayüzü, farklı türde kuponların sahip olması gereken metotları tanımlar.
interface Coupon {
    String code();  // Kupon kodu döndürür.
    String message();  // Kuponun mesajını döndürür.
}

// FoodCoupon sınıfı, Coupon arayüzünü implement eder ve gıda kuponları için özel davranışlar tanımlar.
class FoodCoupon implements Coupon {
    @Override
    public String code() {
        // UUID.randomUUID().toString() metodu ile benzersiz bir kupon kodu oluşturur.
        return UUID.randomUUID().toString();
    }

    @Override
    public String message() {
        // Gıda kuponu için mesajı döndürür.
        return "I am a food coupon";
    }
}

// ElectronicsCoupon sınıfı, Coupon arayüzünü implement eder ve elektronik kuponları için özel davranışlar tanımlar.
class ElectronicsCoupon implements Coupon {
    @Override
    public String code() {
        // UUID.randomUUID().toString() metodu ile benzersiz bir kupon kodu oluşturur.
        return UUID.randomUUID().toString();
    }

    @Override
    public String message() {
        // Elektronik kuponu için mesajı döndürür.
        return "I am an electronics coupon";
    }
}

// CouponFactory sınıfı, kuponları oluşturmak için kullanılan factory sınıfıdır.
class CouponFactory {
    // Bu metot, verilen puan değerine göre uygun kuponu oluşturur.
    public static Coupon getCoupon(int points) {
        // Eğer puan 50'den küçükse, gıda kuponu oluşturulur.
        if (points < 50) {
            return new FoodCoupon();
        }
        // Eğer puan 50 veya daha büyükse, elektronik kuponu oluşturulur.
        return new ElectronicsCoupon();
    }
}

// Factory sınıfı, factory deseni ile kuponlar oluşturulmasını sağlar.
public class Factory {
    public static void main(String[] args) {
        // 30 puan ile bir kupon oluşturulur. Bu durumda bir FoodCoupon oluşturulacak.
        Coupon coupon1 = CouponFactory.getCoupon(30);
        System.out.println(String.format("Coupon code: %s, message: %s", coupon1.code(), coupon1.message()));

        // 50 puan ile bir kupon oluşturulur. Bu durumda bir ElectronicsCoupon oluşturulacak.
        Coupon coupon2 = CouponFactory.getCoupon(50);
        System.out.println(String.format("Coupon code: %s, message: %s", coupon2.code(), coupon2.message()));
    }
}
