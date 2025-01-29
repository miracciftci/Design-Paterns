package designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

/*
Observer Pattern, bir nesnede değişiklik olduğunda, bu değişiklikten diğer bağlı nesnelerin (gözlemcilerin)
haberdar edilmesini sağlayan bir tasarım desenidir. Bu desen, yayıncı-abone (publisher-subscriber) modeline
benzer bir şekilde çalışır. Bir nesne üzerinde değişiklik meydana geldiğinde, kendisine bağlı olan tüm
gözlemciler otomatik olarak bilgilendirilir.
 */

public class Observer {
    // Channel interface, haber almak isteyen tüm kanal sınıflarının uygulaması gereken metotları tanımlar.
    interface Channel {
        void update(String news);  // Haber alındığında bu metot çalışır.
        void printNews();  // Kanalın almış olduğu haberleri yazdırır.
    }

    // NewsAgency, haber sağlayan ve kanallara bildirim gönderen sınıftır (Subject).
    class NewsAgency {
        private String news;  // Ajansın güncel haberini tutar.
        private List<Channel> channels = new ArrayList<>();  // Haberleri alacak tüm kanalların listesi.

        // Yeni bir kanal abone etmek için kullanılır.
        public void addObserver(Channel channel) {
            this.channels.add(channel);  // Kanalı gözlemci listesine ekler.
        }

        // Bir kanalı gözlemci listesinde çıkarır.
        public void removeObserver(Channel channel) {
            this.channels.remove(channel);  // Kanalı gözlemci listesinden siler.
        }

        // Yeni bir haber geldiğinde bu metot çağrılır.
        public void setNews(String news) {
            this.news = news;  // Ajansın haberini günceller.
            // Tüm kanallara haber gönderilir.
            for (Channel channel : this.channels) {
                channel.update(this.news);  // Her kanala güncel haber gönderilir.
            }
        }
    }

    // NewsChannel sınıfı, Channel arayüzünü implemente eder ve gelen haberleri saklar.
    class NewsChannel implements Channel {
        private List<String> newsList = new ArrayList<>();  // Kanalda biriken haberlerin listesi.

        // Gelen haberleri saklar.
        @Override
        public void update(String news) {
            newsList.add(news);  // Gelen haber, kanala eklenir.
        }

        // Kanalda biriken tüm haberleri yazdırır.
        @Override
        public void printNews() {
            for (String news : newsList) {
                System.out.println(news);  // Kanalın haberleri ekrana yazdırılır.
            }
        }
    }

    // Observer pattern'ini gösteren demo metodu.
    void observerDemo() {
        // Üç tane kanal oluşturulur.
        Channel channel1 = new NewsChannel();
        Channel channel2 = new NewsChannel();
        Channel channel3 = new NewsChannel();

        // NewsAgency ajansı oluşturulur.
        NewsAgency agency = new NewsAgency();

        // Kanallar ajansa abone edilir.
        agency.addObserver(channel1);
        agency.addObserver(channel2);
        agency.addObserver(channel3);

        // Ajans, haberlerini günceller.
        agency.setNews("news 1");  // Ajans, "news 1" haberini yayınlar.
        agency.setNews("news 2");  // Ajans, "news 2" haberini yayınlar.
        agency.setNews("news 3");  // Ajans, "news 3" haberini yayınlar.

        // Kanalların her biri, aldıkları haberleri yazdırır.
        channel1.printNews();  // channel1, aldığı haberleri yazdırır.
        channel2.printNews();  // channel2, aldığı haberleri yazdırır.
        channel3.printNews();  // channel3, aldığı haberleri yazdırır.
    }

    public static void main(String[] args) {
        Observer observer = new Observer();
        observer.observerDemo();  // Observer demo fonksiyonu çağrılır.
    }

}
