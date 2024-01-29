class Philosopher implements Runnable {
     int pn;
     Fork lf;
     Fork rf;

    public Philosopher(int n, Fork[] forks) {
        this.pn = n;
        this.lf = forks[n];
        this.rf = forks[(n + 1) % forks.length];
    }

    @Override
    public void run() {
        while (true) {
            waiting();
            synchronized (lf) {
                lf.achieve(pn);
                synchronized (rf) {
                    rf.achieve(pn);
                    consume();
                    rf.release();
                }
                lf.release();
            }
        }
    }

    private void waiting() {
        System.out.println("Philosopher " + pn + " is thinking.");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void consume() {
        System.out.println("Philosopher " + pn + " is eating.");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException error ) {

        }
    }
}