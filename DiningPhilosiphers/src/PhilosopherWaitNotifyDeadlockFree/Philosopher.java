package PhilosopherWaitNotifyDeadlockFree;

import PhilosopherWaitNotify.Chopstick;

import java.util.Random;

public class Philosopher extends Thread {

    PhilosopherWaitNotify.Chopstick leftChopstick;
    PhilosopherWaitNotify.Chopstick rightChopstick;
//    boolean hasEaten;
    int pID;

    public Philosopher(PhilosopherWaitNotify.Chopstick left, Chopstick right, int pID) {
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

                // Since we have an issue with philosophers possibly picking up all at the same time.
                // Let's have every other philosopher pick up from the left, and the other hand from the right.
                // Now if we have a collision. only the adjacent philosophers wil wait, not all of them
                if (pID % 2 == 0) {
                    leftChopstick.pickUp();
                    rightChopstick.pickUp();
                }
                else {
                    rightChopstick.pickUp();
                    leftChopstick.pickUp();
                }
                System.out.println("PhilosopherWaitNotify.Philosopher #" + pID + " finished eating!");

                if (pID % 2 == 0) {
                    leftChopstick.putBack();
                    rightChopstick.putBack();
                }
                else {
                    rightChopstick.putBack();
                    leftChopstick.putBack();
                }

                return;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
