package designpatterns.behavioral;

/*
Chain of Responsibility deseni, iş mantığını esnek ve modüler hale getirerek
bir isteğin zincir boyunca aktarılmasını sağlar. Teknik destek, hata yönetimi,
yetkilendirme gibi senaryolarda yaygın olarak kullanılır.
 */

public class ChainOfResponsibility {

    // Abstract Approver class, loan onaylama sorumluluğuna sahip sınıfların temelini oluşturur
    abstract class Approver {
        Approver chief; // Onayı verecek bir üst yöneticiyi tutar

        // Yapıcı metod, her Approver nesnesi için bir üst yönetici belirler
        public Approver(Approver chief) {
            this.chief = chief;
        }

        // Soyut metod, her alt sınıf kendi onaylama işlevini burada implemente edecektir
        abstract boolean approveLoan(int amount);
    }

    // Officer (Memur) sınıfı, Approver sınıfını extend eder ve 100 birime kadar olan kredileri onaylar
    class Officer extends Approver {

        public Officer(Approver chief) {
            super(chief); // Officer'ın üst yöneticisini ayarlamak için temel sınıfın yapıcısını çağırır
        }

        @Override
        boolean approveLoan(int amount) {
            // Eğer istenen kredi miktarı 100'den küçükse, Officer onay verebilir
            if (amount < 100) {
                System.out.println("Officer approves");
                return true;
            } else if (chief != null) {
                // Eğer miktar Officer'ın onaylayabileceğinden büyükse, işin bir üst yöneticisine devredilir
                System.out.println("Amount is greater than officer can approve. Passing to chief");
                return chief.approveLoan(amount); // Chief onayı verip veremediğini kontrol eder
            }
            return false; // Onay verilemiyorsa, false döner
        }
    }

    // Manager (Müdür) sınıfı, Approver sınıfını extend eder ve 500 birime kadar olan kredileri onaylar
    class Manager extends Approver {

        public Manager(Approver chief) {
            super(chief); // Manager'ın üst yöneticisini ayarlamak için temel sınıfın yapıcısını çağırır
        }

        @Override
        boolean approveLoan(int amount) {
            // Eğer istenen kredi miktarı 500'den küçükse, Manager onay verebilir
            if (amount < 500) {
                System.out.println("Manager approves");
                return true;
            } else if (chief != null) {
                // Eğer miktar Manager'ın onaylayabileceğinden büyükse, işin bir üst yöneticisine devredilir
                System.out.println("Amount is greater than manager can approve. Passing to chief");
                return chief.approveLoan(amount); // Chief onayı verip veremediğini kontrol eder
            }
            return false; // Onay verilemiyorsa, false döner
        }
    }

    // Executive (Yönetici) sınıfı, onaylama zincirinin son halkasıdır ve her miktarı onaylar
    class Executive extends Approver {

        public Executive() {
            super(null); // Executive, en üst düzeyde olduğu için başka bir üst yöneticisi yoktur
        }

        @Override
        boolean approveLoan(int amount) {
            // Executive her miktarı onaylar
            System.out.println("Executive approves");
            return true;
        }
    }

    // Bu metod, onaylama zincirini çalıştırarak örnek kredileri onaylar
    void chainOfResponsibilityDemo(){
        // Zincirin son halkası olan Executive'yi oluşturuyoruz
        Approver executive = new Executive();

        // Executive'i üst yönetici olarak alan bir Manager nesnesi oluşturuyoruz
        Approver manager = new Manager(executive);

        // Manager'ı üst yönetici olarak alan bir Officer nesnesi oluşturuyoruz
        Approver officer = new Officer(manager);

        // Officer, 50 birimlik bir kredi onaylıyor
        officer.approveLoan(50);

        // Officer, 450 birimlik bir kredi onaylıyor
        officer.approveLoan(450);

        // Officer, 1050 birimlik bir kredi için Executive'e yönlendiriyor
        officer.approveLoan(1050);
    }

    public static void main(String[] args) {
        ChainOfResponsibility cor = new ChainOfResponsibility();
        cor.chainOfResponsibilityDemo(); // Zinciri çalıştır
    }
}
