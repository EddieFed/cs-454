package PhilosopherWaitNotify;

public class Chopstick {

    boolean held;

    public Chopstick() {
        this.held = false;
    }

    public synchronized void pickUp() throws InterruptedException {
        while (this.held) {
            wait(); // Wait indefinitely until other thread says they put down chopstick
        }
        this.held = true;
    }

    public synchronized void putBack() {
        this.held = false;
        notify();   // Tell other threads that a chopstick is available!
    }

}
