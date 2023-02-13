package PhilosopherWaitNotifyDeadlockFree;

import PhilosopherWaitNotify.Chopstick;
import PhilosopherWaitNotify.Philosopher;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<PhilosopherWaitNotify.Philosopher> philosophers = new ArrayList<>();
        ArrayList<PhilosopherWaitNotify.Chopstick> chopsticks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
           chopsticks.add(new Chopstick());
        }

        philosophers.add(new PhilosopherWaitNotify.Philosopher(chopsticks.get(4), chopsticks.get(0), 0));
        for (int i = 1; i < 5; i++) {
            philosophers.add(new PhilosopherWaitNotify.Philosopher(chopsticks.get(i - 1), chopsticks.get(i), i));
        }

        for (PhilosopherWaitNotify.Philosopher p : philosophers) {
            p.start();  // Start all philosophers!
        }

        for (Philosopher p : philosophers) {
            p.join();   // Wait for all threads to complete!
                        // for the sake of debugging and testing!
        }

        System.out.println("All philosophers have eaten!");

    }
}