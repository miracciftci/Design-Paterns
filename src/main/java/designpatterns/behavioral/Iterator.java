package designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

/*
collection elamanlarında gezinmeyi sağlar. javada çok kullanılır.
 */

public class Iterator {

    // IIterator arayüzü, iterator pattern'inin temelini oluşturur.
    // Iterator sınıfı, koleksiyonlar üzerinde gezinti yapmayı sağlar.
    interface IIterator{
        boolean hasNext(); // Bir sonraki öğe olup olmadığını kontrol eder.
        Object next();     // Bir sonraki öğeyi döndürür.
    }

    // Container arayüzü, bir koleksiyona sahip olmalı ve bir iterator sağlamalıdır.
    interface Container {
        IIterator getIterator(); // Koleksiyon üzerinde gezinmek için bir iterator döndüren metod.
    }

    // StringCollection sınıfı, bir dizi stringi tutar ve üzerinde gezinilebilecek bir iterator sağlar.
    class StringCollection implements Container{
        private List<String> strings = new ArrayList<>(); // String listesi, koleksiyonu oluşturur.

        // Listede yeni bir string eklemek için bir metod.
        void addString(String string){
            strings.add(string);
        }

        // String koleksiyonu üzerinde gezinmek için iterator döndüren metod.
        @Override
        public IIterator getIterator() {
            return new StringCollectionIterator(strings); // Koleksiyon üzerinde gezinmek için iterator oluşturur.
        }
    }

    // StringCollectionIterator sınıfı, IIterator arayüzünü implement eder ve koleksiyon üzerinde gezinmeyi sağlar.
    class StringCollectionIterator implements IIterator{
        private List<String> strings; // String listesi
        private int pos = 0; // Gezinme için bir pozisyon belirler.

        // Yapıcı metod, iterator nesnesi oluşturulurken koleksiyonu alır.
        public StringCollectionIterator(List<String> strings) {
            this.strings = strings;
        }

        // hasNext metodu, koleksiyon içinde bir sonraki öğe olup olmadığını kontrol eder.
        @Override
        public boolean hasNext() {
            // Koleksiyon sonuna gelip gelmediğini kontrol eder veya öğe null değilse devam eder.
            if(pos >= strings.size() || strings.get(pos) == null){
                return false;
            }
            return true;
        }

        // next metodu, koleksiyondaki bir sonraki öğeyi döndürür.
        @Override
        public Object next() {
            String string = strings.get(pos); // Geçerli öğeyi alır
            pos = pos + 1; // Pozisyonu bir artırır.
            return string; // Öğeyi döndürür.
        }
    }

    // iteratorDemo metodu, Iterator pattern'ini örnekle gösterir.
    void iteratorDemo(){
        // StringCollection nesnesi oluşturuluyor ve koleksiyona stringler ekleniyor.
        StringCollection stringCollection = new StringCollection();
        stringCollection.addString("Str 1");
        stringCollection.addString("Str 2");
        stringCollection.addString("Str 3");
        stringCollection.addString("Str 4");
        stringCollection.addString("Str 5");

        // Koleksiyon üzerinde gezinmek için iterator alınıyor.
        IIterator iterator = stringCollection.getIterator();

        // Iterator kullanılarak koleksiyon üzerinde geziliyor ve öğeler yazdırılıyor.
        while (iterator.hasNext()){
            System.out.println(iterator.next()); // Her bir öğeyi ekrana yazdırır.
        }
    }

    public static void main(String[] args) {
        Iterator iterator = new Iterator();
        iterator.iteratorDemo(); // Iterator pattern'ini çalıştıran metod
    }
}

