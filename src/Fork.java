import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Fork {
    private final Lock l = new ReentrantLock();
     int f;

    public Fork(int f) {
        this.f = f;
    }

    public void achieve(int pn) {
        l.lock();
        System.out.println("Philosopher " + pn + " picks up fork " + f);
    }

    public void release() {
        l.unlock();
    }
}
