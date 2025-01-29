package designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

/*
Visitor Pattern, nesne yapısını değiştirmeden ona yeni işlemler (fonksiyonlar)
eklemeyi sağlayan bir tasarım desenidir. Bu desen sayesinde, nesne sınıflarına
dokunmadan farklı işlemleri uygulayabilirsiniz.
*/

public class Visitor {

    // Node arayüzü, tüm nesnelerin display metoduna sahip olmasını sağlar
    interface Node {
        void display();  // Her Node türünün kendine özgü display metodunu implement etmesi beklenir
    }

    // City sınıfı, Node arayüzünü implement eder ve display metodunu uygular
    class City implements Node {
        @Override
        public void display() {
            System.out.println("This is a city");
        }
    }

    // IndustrialZone sınıfı, Node arayüzünü implement eder ve display metodunu uygular
    class IndustrialZone implements Node {
        @Override
        public void display() {
            System.out.println("This is an industrial zone");
        }
    }

    // Stadium sınıfı, Node arayüzünü implement eder ve display metodunu uygular
    class Stadium implements Node {
        @Override
        public void display() {
            System.out.println("This is a stadium");
        }
    }

    // Graph sınıfı, Node arayüzünü implement eder ve Node nesnelerinin bir koleksiyonunu tutar
    class Graph implements Node {
        List<Node> nodes = new ArrayList<>();  // Node nesnelerini saklamak için liste

        // nodes listesine Node eklemeye yarayan metod
        public List<Node> getNodes() {
            return nodes;
        }

        // Node nesnesi ekleyen metod
        void addNode(Node node) {
            nodes.add(node);
        }

        // Graph üzerindeki tüm Node nesnelerini display metoduyla görüntüler
        @Override
        public void display() {
            for (Node node : nodes) {
                node.display();  // Her bir node için display metodunu çağırır
            }
        }
    }

    // ExporterVisitor arayüzü, her tür Node nesnesini dışa aktarmak için metotlar tanımlar
    interface ExporterVisitor {
        void exportGraph(Graph graph);  // Graph nesnesi dışa aktarılır
        void exportCity(City city);  // City nesnesi dışa aktarılır
        void exportIndustrialZone(IndustrialZone industrialZone);  // IndustrialZone nesnesi dışa aktarılır
        void exportStadium(Stadium stadium);  // Stadium nesnesi dışa aktarılır
    }

    // XmlExporter sınıfı, ExporterVisitor arayüzünü implement eder ve her tür Node'yu XML formatında dışa aktarır
    class XmlExporter implements ExporterVisitor {

        // Graph nesnesini dışa aktarır
        @Override
        public void exportGraph(Graph graph) {
            List<Node> nodes = graph.getNodes();  // Graph içindeki Node nesnelerini al
            for (Node node : nodes) {
                if (node instanceof City) {
                    exportCity((City) node);  // City türündeki node'ları dışa aktar
                } else if (node instanceof IndustrialZone) {
                    exportIndustrialZone((IndustrialZone) node);  // IndustrialZone türündeki node'ları dışa aktar
                } else if (node instanceof Stadium) {
                    exportStadium((Stadium) node);  // Stadium türündeki node'ları dışa aktar
                } else {
                    System.out.println("Unknown node type");  // Tanımlanmış olmayan türler için hata mesajı
                }
            }
        }

        // City nesnesini XML formatında dışa aktarır
        @Override
        public void exportCity(City city) {
            System.out.println("Exporting city in xml format");
            city.display();  // City'nin display metodunu çağırarak bilgiyi göster
        }

        // IndustrialZone nesnesini XML formatında dışa aktarır
        @Override
        public void exportIndustrialZone(IndustrialZone industrialZone) {
            System.out.println("Exporting industrial zone in xml format");
            industrialZone.display();  // IndustrialZone'un display metodunu çağırarak bilgiyi göster
        }

        // Stadium nesnesini XML formatında dışa aktarır
        @Override
        public void exportStadium(Stadium stadium) {
            System.out.println("Exporting stadium in xml format");
            stadium.display();  // Stadium'un display metodunu çağırarak bilgiyi göster
        }
    }

    // Visitor pattern demo metodunun çalıştığı ana fonksiyon
    void visitorDemo() {
        Node city = new City();  // City nesnesi oluşturuluyor
        Node industrialZone = new IndustrialZone();  // IndustrialZone nesnesi oluşturuluyor
        Node stadium = new Stadium();  // Stadium nesnesi oluşturuluyor

        // Graph nesnesi oluşturuluyor ve farklı Node'lar ekleniyor
        Graph graph = new Graph();
        graph.addNode(city);
        graph.addNode(industrialZone);
        graph.addNode(stadium);

        // XML formatında dışa aktarmak için bir XmlExporter nesnesi oluşturuluyor
        ExporterVisitor exporterVisitor = new XmlExporter();
        exporterVisitor.exportGraph(graph);  // Graph nesnesini XML formatında dışa aktarmak için exporterVisitor kullanılıyor
    }

    public static void main(String[] args) {
        Visitor visitor = new Visitor();
        visitor.visitorDemo();  // visitorDemo metodunu çalıştırarak Visitor pattern'i demo et
    }
}
