package designpatterns.behavioral;

/*
Template Method Pattern, bir algoritmanın ana iskeletini belirleyerek, bazı adımların
alt sınıflar tarafından özelleştirilmesini sağlar. Bu desen, tekrar eden kodları azaltır
ve kodun daha kolay genişletilmesine olanak tanır.
 */

public class TemplateMethod {

    // Şablon sınıf (Template Class)
    abstract class Game {
        // Oyunu başlatma adımı
        abstract void initialize();

        // Oyunun oynanma adımı
        abstract void startPlay();

        // Oyunun bitirilme adımı
        abstract void endPlay();

        // Şablon metot (Template Method)
        // Oyunun oynanma sürecini belirler, alt sınıflar bu sırayı değiştiremez.
        public final void play(){
            initialize();  // Oyunu başlat
            startPlay();   // Oyunu oyna
            endPlay();     // Oyunu bitir
        }
    }

    // Kriket oyununu temsil eden sınıf
    class Cricket extends Game {
        @Override
        void initialize() {
            System.out.println("Cricket Game Initialized! Start playing.");
        }

        @Override
        void startPlay() {
            System.out.println("Cricket Game Started. Enjoy the game!");
        }

        @Override
        void endPlay() {
            System.out.println("Cricket Game Finished!");
        }
    }

    // Futbol oyununu temsil eden sınıf
    class Football extends Game {
        @Override
        void initialize() {
            System.out.println("Football Game Initialized! Start playing.");
        }

        @Override
        void startPlay() {
            System.out.println("Football Game Started. Enjoy the game!");
        }

        @Override
        void endPlay() {
            System.out.println("Football Game Finished!");
        }
    }

    // Şablon metodun nasıl çalıştığını gösteren demo fonksiyonu
    void templateDemo(){
        // Kriket oyununu başlat
        Game game = new Cricket();
        game.play(); // Template method çağrılıyor (initialize -> startPlay -> endPlay)

        System.out.println(); // Boş satır

        // Futbol oyununu başlat
        game = new Football();
        game.play(); // Template method çağrılıyor (initialize -> startPlay -> endPlay)
    }

    public static void main(String[] args) {
        TemplateMethod templateMethod = new TemplateMethod();
        templateMethod.templateDemo(); // Oyunları test eden metodu çalıştır
    }
}
