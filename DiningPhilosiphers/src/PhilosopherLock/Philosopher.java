package PhilosopherLock;

import java.util.Random;

public class Philosopher extends Thread {

    Chopstick leftChopstick;
    Chopstick rightChopstick;
//    boolean hasEaten;
    int pID;

    public Philosopher(Chopstick left, Chopstick right, int pID) {
        this.leftChopstick = left;
        this.rightChopstick = right;
//        this.hasEaten = true;
        this.pID = pID;
    }

    @Override
    public void run() {
        Random random = new Random();   // Since the PhilosopherWaitNotify.Philosopher "thinks" before eating
                                        // we can simulate this with a rand int
        while (true) {
            try {
                Thread.sleep(random.nextInt(10000));  // PhilosopherWaitNotify.Philosopher "thinks"

                System.out.println("PhilosopherWaitNotify.Philosopher #" + pID + " is hungry, attempting to eat!");

                // This is susceptible to deadlock since everyone picks up the same chopstick first, imagine if everyone
                // picked the left one up and now was stuck waiting for the right to become available. Nobody wins...
                leftChopstick.pickUp();
                rightChopstick.pickUp();

                System.out.println("PhilosopherWaitNotify.Philosopher #" + pID + " finished eating!");

                leftChopstick.putBack();
                rightChopstick.putBack();

                return;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
