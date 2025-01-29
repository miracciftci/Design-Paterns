package designpatterns.behavioral;

/*
State Pattern, bir nesnenin iç durumuna bağlı olarak davranışını değiştirmesine
olanak tanıyan bir tasarım desenidir. Nesne, farklı durumlara geçiş yaparak ilgili
davranışı sergileyebilir. Bu desen, if-else veya switch-case karmaşıklığını azaltarak,
esnek ve genişletilebilir bir yapı oluşturmayı sağlar.
 */

public class State {

    // State Pattern'in arayüzü: Her durumun uygulanması gereken metotları içerir.
    interface PackageState {
        void next(Package pkg);   // Paketi bir sonraki duruma geçirir.
        void prev(Package pkg);   // Paketi bir önceki duruma geçirir.
        void printStatus();       // Paketin mevcut durumunu ekrana yazdırır.
    }

    // Paket sınıfı, bir paketin durumunu yönetir.
    class Package {
        private PackageState state = new OrderedState(); // Başlangıç durumu: OrderedState

        public PackageState getState() {
            return state;
        }

        public void setState(PackageState state) {
            this.state = state;
        }

        // Paketi bir önceki duruma geçirir.
        public void previousState() {
            state.prev(this);
        }

        // Paketi bir sonraki duruma geçirir.
        public void nextState() {
            state.next(this);
        }

        // Mevcut durumu ekrana yazdırır.
        public void printStatus() {
            state.printStatus();
        }
    }

    // "Sipariş edildi" (OrderedState) durumu
    class OrderedState implements PackageState {

        @Override
        public void next(Package pkg) {
            pkg.setState(new DeliveredState()); // Bir sonraki duruma (DeliveredState) geçir.
        }

        @Override
        public void prev(Package pkg) {
            System.out.println("The package is in its root state."); // Önceki durum olmadığı için uyarı verir.
        }

        @Override
        public void printStatus() {
            System.out.println("Package ordered, not delivered to the office yet.");
        }
    }

    // "Teslim Edildi" (DeliveredState) durumu
    class DeliveredState implements PackageState {

        @Override
        public void next(Package pkg) {
            pkg.setState(new ReceivedState()); // Bir sonraki duruma (ReceivedState) geçir.
        }

        @Override
        public void prev(Package pkg) {
            pkg.setState(new OrderedState()); // Önceki duruma (OrderedState) geri döner.
        }

        @Override
        public void printStatus() {
            System.out.println("Package delivered to post office, not received yet.");
        }
    }

    // "Teslim Alındı" (ReceivedState) durumu
    class ReceivedState implements PackageState {

        @Override
        public void next(Package pkg) {
            System.out.println("This package is already received by a client."); // Son durum olduğu için ilerleyemez.
        }

        @Override
        public void prev(Package pkg) {
            pkg.setState(new DeliveredState()); // Önceki duruma (DeliveredState) geri döner.
        }

        @Override
        public void printStatus() {
            System.out.println("Package is received.");
        }
    }

    // State Pattern'in nasıl çalıştığını gösteren bir demo metodu
    void stateDemo() {
        Package pkg = new Package(); // Yeni bir paket oluşturulur.
        pkg.printStatus(); // Mevcut durumu yazdır.

        pkg.nextState(); // Paketi bir sonraki duruma geçir.
        pkg.printStatus(); // Yeni durumu yazdır.

        pkg.nextState(); // Paketi bir sonraki duruma geçir.
        pkg.printStatus(); // Yeni durumu yazdır.

        pkg.nextState(); // Son durumda olduğu için daha ileri gidemez.
        pkg.printStatus(); // Mevcut durumu yazdır.
    }

    public static void main(String[] args) {
        State state = new State();
        state.stateDemo(); // State pattern örneğini çalıştır.
    }
}
