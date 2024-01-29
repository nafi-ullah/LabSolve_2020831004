


public class Main {
    public static void main(String[] args) {
        int numPhilosophers = 3;
        Fork[] forks = new Fork[numPhilosophers];
        Philosopher[] philosophers = new Philosopher[numPhilosophers];

        // Create forks with locks
        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new Fork(i);
        }

        // Create philosophers and start their threads
        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new Philosopher(i, forks);
            new Thread(philosophers[i]).start(); // Start philosopher threads
        }
    }
}