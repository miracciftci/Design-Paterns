package designpatterns.behavioral;

/*
Bir nesnenin mevcut durumunu kaydederek, daha sonra bu duruma geri
dönülmesini sağlar. Geri alma (undo) işlemlerini kolaylaştırmak,
nesnenin durumunu saklamak için kullanılır.
 */

public class Memento {

    // Originator: Durum bilgisi tutan ve geri alınabilir durumda olan nesne
    public class TextWindow {

        StringBuilder currentText; // TextWindow nesnesinin mevcut metni

        public TextWindow() {
            this.currentText = new StringBuilder(); // Başlangıçta boş bir metin
        }

        // Metin ekleme fonksiyonu
        void addText(String text) {
            currentText.append(text); // Yeni metin ekleniyor
        }

        // Mevcut metni döndürme fonksiyonu
        String getText(){
            return currentText.toString(); // Mevcut metni döndür
        }

        // TextWindow'nun mevcut durumunu kaydeder ve bir memento oluşturur
        public TextWindowState save() {
            return new TextWindowState(currentText.toString()); // TextWindowState nesnesi döndürülür
        }

        // Kaydedilen durumu geri yükler
        public void restore(TextWindowState save) {
            currentText = new StringBuilder(save.getText()); // Kaydedilen durumu geri yükler
        }
    }

    // Caretaker: Durumların kaydını tutar ve geri alır
    class TextEditor {

        TextWindow textWindow; // TextWindow nesnesi, metin alanı
        TextWindowState savedTextWindow; // Kaydedilen durum

        // TextEditor nesnesinin yapıcı fonksiyonu
        public TextEditor(TextWindow textWindow) {
            this.textWindow = textWindow; // TextWindow nesnesi başlatılır
        }

        // Yazma fonksiyonu, metin ekler
        void write(String text) {
            textWindow.addText(text); // TextWindow'a metin ekler
        }

        // Mevcut metni yazdırma fonksiyonu
        void print(){
            System.out.println(textWindow.getText()); // Mevcut metni ekrana yazdırır
        }

        // Mevcut durumu kaydetme fonksiyonu
        void hitSave() {
            savedTextWindow = textWindow.save(); // Metnin mevcut durumu kaydedilir
        }

        // Son kaydedilen durumu geri alma fonksiyonu
        void hitUndo() {
            textWindow.restore(savedTextWindow); // Son kaydedilen durumu geri yükler
        }
    }

    // Memento: Kaydedilen durum nesnesi
    class TextWindowState {

        String text; // Metnin kaydedilen hali

        public TextWindowState(String text) {
            this.text = text; // Metnin kaydedilmesi
        }

        // Kaydedilen metni döndürme fonksiyonu
        String getText() {
            return text; // Kaydedilen metni döndürür
        }
    }

    // Memento desenini gösteren demo fonksiyonu
    void mementoDemo() {
        // TextWindow nesnesi oluşturulur ve TextEditor ile ilişkilendirilir
        TextEditor textEditor = new TextEditor(new TextWindow());

        // Metin yazılmaya başlanır
        textEditor.write("The Memento Design Pattern\n");
        textEditor.write("How to implement it in Java?\n");

        // Mevcut durum kaydedilir
        textEditor.hitSave();

        // Yeni metin eklenir
        textEditor.write("Buy milk and eggs before coming home\n");

        // Mevcut metin yazdırılır
        textEditor.print();

        // Geri alma (undo) işlemi yapılır, kaydedilen durum geri yüklenir
        textEditor.hitUndo();

        // Geri alınan metin yazdırılır
        textEditor.print();
    }

    public static void main(String[] args) {
        Memento memento = new Memento();
        memento.mementoDemo(); // Memento desenini çalıştıran metod
    }
}
