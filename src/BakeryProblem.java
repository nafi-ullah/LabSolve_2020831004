import java.util.Random;

class Bakery {
     int x = 0;

    public synchronized void Bread() {
        x++;
        System.out.println("One bread is ready. stocked bread: " + x);
        notify();
    }

    public synchronized void sold(String sN) {
        while (x <= 0) {
            try {
                System.out.println(sN + " is waiting ");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        x--;
        System.out.println(sN + " got bread. stocked bread: " + x);
    }
}

class BakeryThread extends Thread {
    private Bakery bakery;

    public BakeryThread(Bakery bakery) {
        this.bakery = bakery;
    }

    public void run() {
        while (true) {
            bakery.Bread();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ShopThread extends Thread {
    private Bakery bakery;
    private String shopName;

    public ShopThread(Bakery bakery, String shopName) {
        this.bakery = bakery;
        this.shopName = shopName;
    }

    public void run() {
        while (true) {
            bakery.sold(shopName);
            try {
                Thread.sleep(new Random().nextInt(5000) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class BakeryProblem {
    public static void main(String[] args) {
        Bakery bakery = new Bakery();
        new BakeryThread(bakery).start();
        for (int i = 1; i <= 3; i++) {
            new ShopThread(bakery, ""+ i + " no Shop " ).start();
        }
    }
}