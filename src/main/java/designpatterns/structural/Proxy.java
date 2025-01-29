package designpatterns.structural;

import java.util.HashMap;
import java.util.Map;

/*
Erişim kontrolünü etkinleştirmek, maliyeti ve karmaşıklığı azaltmak için
bir nesneyi başka bir nesneyle nasıl temsil etmeniz gerektiği ile ilgilidir.

https://stackoverflow.com/questions/37692814/what-is-the-exact-difference-between-adapter-and-proxy-patterns
 */

public class Proxy {

    // Video sınıfı, video objesini temsil eder.
    class Video {
        // Video ile ilgili özellikler ve metodlar eklenebilir.
    }

    // YoutubeService arayüzü, video almak için kullanılan temel servisi tanımlar.
    interface YoutubeService {
        Video getVideo(String url);  // URL'ye göre video alma metodu.
    }

    // YoutubeServiceImp sınıfı, YoutubeService arayüzünün gerçek implementasyonudur.
    class YoutubeServiceImp implements YoutubeService {
        @Override
        public Video getVideo(String url) {
            // Gerçek video alımı burada simüle edilir, örneğin internetten video çekilebilir.
            System.out.println("Fetching video from Youtube: " + url);
            return new Video();  // Gerçek video nesnesi döndürülür.
        }
    }

    // CachedYoutubeService sınıfı, YoutubeService'nin bir proxy'sidir.
    // Bu sınıf, YoutubeServis'i kullanarak videoları cache'ler (önbelleğe alır) ve sonraki talepleri önbellekten alır.
    class CachedYoutubeService implements YoutubeService {
        YoutubeService youtubeService;  // Gerçek Youtube servisini tutan referans.
        Map<String, Video> cache = new HashMap<>();  // Videoları önbellekte tutmak için bir Map.

        // Yapıcı metod: Gerçek Youtube servisinin bir nesnesini alır ve kullanır.
        public CachedYoutubeService(YoutubeService youtubeService) {
            this.youtubeService = youtubeService;
        }

        @Override
        public Video getVideo(String url) {
            // Eğer video önbellekte varsa, direkt önbellekten alınır.
            if (cache.containsKey(url)) {
                System.out.println("Returning cached video for: " + url);
                return cache.get(url);
            }

            // Eğer video önbellekte yoksa, gerçek servisten alınır ve önbelleğe eklenir.
            System.out.println("Fetching video from Youtube and caching it: " + url);
            Video video = youtubeService.getVideo(url);
            cache.put(url, video);  // Yeni video önbelleğe eklenir.
            return video;
        }
    }

    // Proxy deseni örneği: Youtube servisini cache'li bir şekilde kullanma.
    public void ProxyDemo(String url) {
        // Gerçek Youtube servisi oluşturuluyor.
        YoutubeService youtubeService = new YoutubeServiceImp();

        // CachedYoutubeService, gerçek servisi sarmalar (proxy) ve önbellek işlevini ekler.
        YoutubeService cachedYoutubeService = new CachedYoutubeService(youtubeService);

        // Video isteniyor, proxy servisi önbelleği kontrol ederek video getiriyor.
        cachedYoutubeService.getVideo(url);  // İlk çağrıda video internetten alınır ve önbelleğe eklenir.
        cachedYoutubeService.getVideo(url);  // İkinci çağrıda video önbellekten alınır.
    }

    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.ProxyDemo("https://youtube.com/video123");  // Video URL'si verilir.
    }
}
