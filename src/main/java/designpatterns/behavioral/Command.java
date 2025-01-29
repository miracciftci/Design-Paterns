package designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

/*
Command Pattern

İstekleri bir nesneye kapsülleyerek isteği sıraya koyma veya geri alma
işlemlerini kolaylaştırır. Bu desenin temel amacı, istemci (client) ile
işlem gerçekleştiren nesneler arasındaki bağı gevşetmektir. İstekler,
Command nesneleri olarak oluşturulur ve bu nesneler, işlemleri geri almayı
veya tekrar yapmayı mümkün hale getirir.
 */

public class Command {

    // Order arayüzü, tüm sipariş türlerinin implement etmesi gereken apply metodunu tanımlar
    interface Order {
        void apply(); // Bu metod, her siparişin uygulanmasını sağlar
    }

    // BuyOrder sınıfı, Order arayüzünü implement eder ve hisse alım işlemi için uygulanacak mantığı içerir
    class BuyOrder implements Order {
        String stock; // Alınacak hisse senedinin adı
        int amount; // Alınacak hisse miktarı

        // Yapıcı metod, hangi hisseyi alacağımızı ve ne kadar alacağımızı belirtir
        public BuyOrder(String stock, int amount) {
            this.stock = stock;
            this.amount = amount;
        }

        // apply metodunu implement eder; bu metod buy (alım) işlemini ekrana yazdırır
        @Override
        public void apply() {
            System.out.println("Buying " + amount + " " + stock + " stock");
        }
    }

    // SellOrder sınıfı, Order arayüzünü implement eder ve hisse satma işlemi için uygulanacak mantığı içerir
    class SellOrder implements Order {
        String stock; // Satılacak hisse senedinin adı
        int amount; // Satılacak hisse miktarı

        // Yapıcı metod, hangi hisseyi satacağımızı ve ne kadar satacağımızı belirtir
        public SellOrder(String stock, int amount) {
            this.stock = stock;
            this.amount = amount;
        }

        // apply metodunu implement eder; bu metod sell (satış) işlemini ekrana yazdırır
        @Override
        public void apply() {
            System.out.println("Selling " + amount + " " + stock + " stock");
        }
    }

    // Broker (Aracı) sınıfı, siparişleri almak ve yürütmekten sorumludur
    class Broker {
        private List<Order> orderList = new ArrayList<>(); // Siparişlerin tutulacağı bir liste

        // Siparişi alır ve sıraya ekler
        public void takeOrder(Order order) {
            orderList.add(order);
        }

        // Tüm alınan siparişleri uygular (her siparişin apply metodunu çalıştırır)
        public void placeOrders() {
            for (Order order : orderList) {
                order.apply(); // Siparişi uygular
            }
            orderList.clear(); // Tüm siparişler uygulandıktan sonra listeyi temizler
        }
    }

    // commandDemo metodu, Command pattern'ini uygulamak için örnek bir kullanım sunar
    void commandDemo(){
        // Alım ve satım işlemlerine dair siparişler oluşturuluyor
        Order buy1 = new BuyOrder("ABC", 10);
        Order buy2 = new BuyOrder("ABC", 20);
        Order sell1 = new SellOrder("ABC", 10);
        Order sell2 = new SellOrder("ABC", 10);

        // Broker (aracı) nesnesi oluşturuluyor
        Broker broker = new Broker();

        // Broker'a alınacak ve satılacak hisse senetleriyle ilgili siparişler veriliyor
        broker.takeOrder(buy1);
        broker.takeOrder(buy2);
        broker.takeOrder(sell1);
        broker.takeOrder(sell2);

        // Broker tüm siparişleri uyguluyor
        broker.placeOrders();
    }

    public static void main(String[] args) {
        Command command = new Command();
        command.commandDemo(); // Command pattern örneğini çalıştırır
    }
}
