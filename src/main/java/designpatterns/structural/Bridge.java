package designpatterns.structural;

/*
Soyutlama ile uygulama arasındaki bağlantıyı ayırarak her ikisinin bağımsız
olarak geliştirilebilmesini sağlar.

https://riptutorial.com/design-patterns/example/14007/bridge-pattern-implementation-in-java
*/

public class Bridge {

    // SteeringWheel arayüzü, araçların yönlerini belirlemek için kullanılır.
    interface SteeringWheel {
        void setDirection(int direction);  // Yönü ayarlamak için setDirection metodu.
    }

    // CarSteeringWheel sınıfı, araba için özel bir direksiyon simidini temsil eder.
    class CarSteeringWheel implements SteeringWheel {
        @Override
        public void setDirection(int direction) {
            // Araba yönünü ayarlamak için kullanılan implementasyon
            System.out.println("Car direction set to " + direction);
        }
    }

    // BoatSteeringWheel sınıfı, tekne için özel bir direksiyon simidini temsil eder.
    class BoatSteeringWheel implements SteeringWheel {
        @Override
        public void setDirection(int direction) {
            // Tekne yönünü ayarlamak için kullanılan implementasyon
            // Tekne için yön, direction * 10 ile ayarlanır.
            System.out.println("Boat direction set to " + direction * 10);
        }
    }

    // Vehicle sınıfı, direksiyon simidini taşıyan ve hareket eden araçların temel sınıfıdır.
    abstract class Vehicle {
        SteeringWheel steeringWheel;  // Aracın direksiyonu

        // Aracın hareket etmesi için abstract bir metot
        abstract void move(int direction, int speed);
    }

    // Car sınıfı, Vehicle sınıfından türetilen ve araba ile ilgili hareket mantığını içeren sınıftır.
    class Car extends Vehicle {
        public Car(SteeringWheel steeringWheel) {
            this.steeringWheel = steeringWheel;  // Araba için bir direksiyon simidi seçilir.
        }

        @Override
        void move(int direction, int speed) {
            // Araba hareket ederken direksiyonun yönünü ayarlamak için steeringWheel kullanılır
            steeringWheel.setDirection(direction);
        }
    }

    // Boat sınıfı, Vehicle sınıfından türetilen ve tekne ile ilgili hareket mantığını içeren sınıftır.
    class Boat extends Vehicle {
        public Boat(SteeringWheel steeringWheel) {
            this.steeringWheel = steeringWheel;  // Tekne için bir direksiyon simidi seçilir.
        }

        @Override
        void move(int direction, int speed) {
            // Tekne hareket ederken direksiyonun yönünü ayarlamak için steeringWheel kullanılır
            steeringWheel.setDirection(direction);
        }
    }

    // Bridge tasarım deseni demo fonksiyonu
    public void bridgeDemo() {
        // Araba için CarSteeringWheel kullanarak bir araba nesnesi oluşturuluyor
        Vehicle car = new Car(new CarSteeringWheel());
        // Araba hareket ettirilir
        car.move(20, 30);

        // Tekne için BoatSteeringWheel kullanarak bir tekne nesnesi oluşturuluyor
        Vehicle boat = new Boat(new BoatSteeringWheel());
        // Tekne hareket ettirilir
        boat.move(100, 200);
    }

    public static void main(String[] args) {
        Bridge bridge = new Bridge();
        bridge.bridgeDemo();  // Bridge deseni demo fonksiyonu çalıştırılır
    }
}
