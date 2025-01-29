package designpatterns.structural;

/*
Adapter tasarım kalıbı, doğrudan bağlanamayan iki uyumsuz arayüzü birbirine bağlamak için kullanılır.
Bir Adapter, mevcut bir sınıfı yeni bir arayüzle sarmalar, böylece ihtiyaç duyulan arayüzle uyumlu hale gelir.

    http://www.vincehuston.org/dp/adapter.html
*/

public class Adapter {

    // LegacyUser sınıfı, eski sistemdeki kullanıcı modelini temsil eder.
    class LegacyUser {
        int id;
        String username;
        String name;
        String lastname;
    }

    // LegacyUserService arayüzü, eski sistemdeki kullanıcı bilgilerini almak için kullanılan servis arayüzüdür.
    interface LegacyUserService {
        LegacyUser getUser(int id);  // Verilen id ile LegacyUser nesnesini döndüren metot.
    }

    // User sınıfı, yeni sistemdeki kullanıcı modelini temsil eder.
    class User {
        int id;
        String email;
        String username;
        String name;
        String lastname;
        String location;
        Boolean isActive;
    }

    // UserRepository arayüzü, yeni sistemdeki kullanıcı verilerini almak için kullanılan veri erişim arayüzüdür.
    interface UserRepository {
        User getUser(String email);  // Verilen email ile User nesnesini döndüren metot.
    }

    // UserService arayüzü, yeni sistemdeki kullanıcı servisini temsil eder.
    interface UserService {
        User getUser(String email);  // Verilen email ile User nesnesini döndüren metot.
    }

    // UserServiceAdapter sınıfı, eski ve yeni sistemlerin uyumsuzluklarını çözmek için kullanılan adapter sınıfıdır.
    class UserServiceAdapter implements UserService {

        // Eski sistemin kullanıcı servisi ve yeni sistemin kullanıcı repository'sine referanslar.
        UserRepository userRepository;
        LegacyUserService legacyUserService;

        @Override
        public User getUser(String email) {
            // Yeni sistemdeki repository'den kullanıcıyı alır.
            User user = userRepository.getUser(email);
            // Eski sistemden kullanıcıyı alır (LegacyUser).
            LegacyUser legacyUser = legacyUserService.getUser(user.id);
            // Eski sistemdeki verilerle yeni kullanıcıyı günceller.
            user.username = legacyUser.username;
            user.lastname = legacyUser.lastname;
            return user;  // Yeni sistemdeki kullanıcıyı döndürür.
        }
    }

    // Adapter deseni ile uyumluluk sağlanarak eski ve yeni sistemler arasındaki entegrasyonu gösterir.
    public void adapterDemo() {
        // UserService arayüzüne sahip yeni bir kullanıcı servisi adapter'ı oluşturulur.
        UserService userService = new UserServiceAdapter();

        // Bir email adresi ile kullanıcı bilgileri alınır.
        User user = userService.getUser("email@email.com");

        // Kullanıcı bilgileri ekrana yazdırılır.
        System.out.println(user);
    }

    public static void main(String[] args) {
        Adapter adapter = new Adapter();
        adapter.adapterDemo();  // Demo fonksiyonu çağrılır ve çalıştırılır.
    }
}
