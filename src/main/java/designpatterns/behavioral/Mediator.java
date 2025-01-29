package designpatterns.behavioral;

import java.util.HashMap;
import java.util.Map;

/*
Mediator Pattern, nesneler arasındaki doğrudan iletişimi ortadan
kaldırarak, iletişimin bir aracı (arabulucu) nesne üzerinden sağlanmasını
amaçlayan bir tasarım desenidir. Bu sayede nesneler birbirine bağımlı olmadan
haberleşebilir ve sistemin bağımlılıkları azaltılmış olur.
 */

public class Mediator {

    // Dispatcher arayüzü, mesajları ilgili aktörlere iletmekle sorumludur.
    interface Dispatcher{
        void dispatch(String topic, String message); // Mesajı ilgili aktöre iletme işlemi
    }

    // Actor arayüzü, bir aktörün mesaj almasını ve mesaj göndermesini sağlar.
    interface Actor{
        void receiveMessage(String message); // Mesaj almak için metod
        void sendMessage(String topic, String message); // Mesaj göndermek için metod
    }

    // MessageDispatcher sınıfı, Dispatcher arayüzünü implement eder ve aktörleri yönetir.
    class MessageDispatcher implements Dispatcher{
        // Aktörleri ve onları ilişkilendiren konu başlıklarını tutan bir harita.
        Map<String, Actor> registeredActors = new HashMap<>();

        // Bir aktörü, bir konu başlığı ile ilişkilendirir.
        void register(String topic, Actor actor){
            registeredActors.put(topic, actor); // Konu başlığı ile aktörü kaydeder
        }

        // Mesajı, doğru konu başlığına sahip aktöre ileten metod.
        @Override
        public void dispatch(String topic, String message) {
            // İlgili konu başlığına sahip aktör bulunur.
            Actor actor = registeredActors.get(topic);
            if(actor == null){
                // Eğer aktör bulunamazsa hata mesajı basılır.
                System.out.println("No actor registered for this topic");
            }else{
                // Aktör varsa, aktöre mesaj gönderilir.
                actor.receiveMessage(message);
            }
        }
    }

    // MessageActor sınıfı, Actor arayüzünü implement eder ve mesaj alıp gönderebilir.
    class MessageActor implements Actor{
        String name; // Aktörün ismi
        Dispatcher dispatcher; // Mesajları iletmek için dispatcher

        // Yapıcı metod, aktörün ismini ve dispatcher'ı alır.
        public MessageActor(String name, Dispatcher dispatcher) {
            this.name = name;
            this.dispatcher = dispatcher;
        }

        // Aktörün mesajı almasını sağlar.
        @Override
        public void receiveMessage(String message) {
            // Aktör mesajı alır ve ekrana yazdırır.
            System.out.println(name + " received message: " + message);
        }

        // Aktörün mesaj göndermesini sağlar.
        @Override
        public void sendMessage(String topic, String message) {
            // Mesaj, dispatcher aracılığıyla ilgili konu başlığına gönderilir.
            dispatcher.dispatch(topic, message);
        }
    }

    // Mediator pattern'ini gösteren demo metodu.
    void mediatorDemo(){
        // Bir MessageDispatcher nesnesi oluşturulur.
        MessageDispatcher dispatcher = new MessageDispatcher();

        // Birkaç MessageActor nesnesi oluşturulur ve dispatcher'a aktarılır.
        Actor actor1 = new MessageActor("Actor 1", dispatcher);
        Actor actor2 = new MessageActor("Actor 2", dispatcher);
        Actor actor3 = new MessageActor("Actor 3", dispatcher);
        Actor actor4 = new MessageActor("Actor 4", dispatcher);

        // Aktörler, dispatcher'a belirli konu başlıklarıyla kaydedilir.
        dispatcher.register("topic1", actor1);
        dispatcher.register("topic2", actor2);
        dispatcher.register("topic3", actor3);
        dispatcher.register("topic4", actor4);

        // Aktörler, dispatcher üzerinden mesaj gönderir.
        actor1.sendMessage("topic2", "Message for topic 2");
        actor1.sendMessage("topic3", "Message for topic 3");
        actor1.sendMessage("topic4", "Message for topic 4");

        actor2.sendMessage("topic1", "Message for topic 1");
        actor2.sendMessage("topic5", "Message for topic 5"); // "topic5" kaydedilmediği için hata mesajı verilir
    }

    public static void main(String[] args) {
        Mediator mediator = new Mediator();
        mediator.mediatorDemo(); // Mediator pattern'ini çalıştıran metod
    }
}
