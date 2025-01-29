package designpatterns.behavioral;

/*
Strategy Pattern (Strateji Deseni), bir işlemi gerçekleştirmek için birden fazla algoritma
veya yöntem arasından seçim yapmayı sağlar. Bu desen sayesinde, algoritmalar dinamik olarak
değiştirilebilir ve kodun esnekliği artırılır.
 */

public class Strategy {

    // 1. STRATEJİ ARAYÜZÜ (INTERFACE)
    // Strategy Pattern'in temelini oluşturur. Farklı matematiksel işlemler için ortak bir interface sağlar.
    interface MathOperation {
        int apply(int a, int b);  // Her matematiksel işlem, iki sayı alıp bir sonuç döndürecektir.
    }

    // 2. STRATEJİ SINIFLARI (CONCRETE STRATEGIES)

    // Toplama işlemi yapan sınıf
    class Add implements MathOperation {
        @Override
        public int apply(int a, int b) {
            return a + b;  // İki sayıyı toplar.
        }
    }

    // Çıkarma işlemi yapan sınıf
    class Subtract implements MathOperation {
        @Override
        public int apply(int a, int b) {
            return a - b;  // İki sayıyı birbirinden çıkarır.
        }
    }

    // 3. CONTEXT SINIFI
    // Kullanıcıya uygun stratejiyi seçme ve çalıştırma imkanı sunar.
    class Context {
        private MathOperation strategy;  // Seçilen stratejiyi tutar.

        // Mevcut stratejiyi döndüren getter metodu
        public MathOperation getStrategy() {
            return strategy;
        }

        // Kullanıcının stratejiyi değiştirmesine olanak tanır
        public void setStrategy(MathOperation strategy) {
            this.strategy = strategy;
        }

        // Kurucu metod, başlangıçta bir strateji belirler
        public Context(MathOperation strategy) {
            this.strategy = strategy;
        }

        // Seçilen stratejiyi kullanarak işlemi gerçekleştirir
        public int executeStrategy(int num1, int num2) {
            return strategy.apply(num1, num2);  // Seçilen algoritmayı çalıştırır.
        }
    }

    // 4. STRATEJİ DESENİNİ DEMO ETMEK İÇİN METOT
    void strategyDemo() {
        // İlk olarak toplama stratejisini kullanarak işlem yapıyoruz
        Context context = new Context(new Add());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        // Stratejiyi çıkarmaya değiştiriyoruz ve tekrar çalıştırıyoruz
        context.setStrategy(new Subtract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));
    }

    public static void main(String[] args) {
        Strategy strategy = new Strategy();  // Strategy sınıfından bir nesne oluşturulur.
        strategy.strategyDemo();  // Strategy Pattern'in çalışma mantığı test edilir.
    }
}
