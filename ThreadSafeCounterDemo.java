import java.util.concurrent.atomic.AtomicInteger;

class UnsafeCounter {
    int count = 0;

    void increment() {
        count++;
    }

    int getCount() {
        return count;
    }
}

class SafeCounter {
    private int count = 0;

    synchronized void increment() {
        count++;
    }

    int getCount() {
        return count;
    }
}

class AtomicCounter {
    private AtomicInteger count = new AtomicInteger(0);

    void increment() {
        count.incrementAndGet();
    }

    int getCount() {
        return count.get();
    }
}

public class ThreadSafeCounterDemo {

    static final int THREADS = 5;
    static final int INCREMENTS = 10000;

    public static void main(String[] args) throws InterruptedException {

        // ---------------- UNSAFE COUNTER ----------------
        UnsafeCounter unsafeCounter = new UnsafeCounter();

        Thread[] unsafeThreads = new Thread[THREADS];

        for (int i = 0; i < THREADS; i++) {

            unsafeThreads[i] = new Thread(() -> {

                for (int j = 0; j < INCREMENTS; j++) {
                    unsafeCounter.increment();
                }

            });

            unsafeThreads[i].start();
        }

        for (Thread thread : unsafeThreads) {
            thread.join();
        }

        // ---------------- THREAD-SAFE COUNTER ----------------
        SafeCounter safeCounter = new SafeCounter();

        Thread[] safeThreads = new Thread[THREADS];

        for (int i = 0; i < THREADS; i++) {

            safeThreads[i] = new Thread(() -> {

                for (int j = 0; j < INCREMENTS; j++) {
                    safeCounter.increment();
                }

            });

            safeThreads[i].start();
        }

        for (Thread thread : safeThreads) {
            thread.join();
        }

        // ---------------- ATOMIC COUNTER ----------------
        AtomicCounter atomicCounter = new AtomicCounter();

        Thread[] atomicThreads = new Thread[THREADS];

        for (int i = 0; i < THREADS; i++) {

            atomicThreads[i] = new Thread(() -> {

                for (int j = 0; j < INCREMENTS; j++) {
                    atomicCounter.increment();
                }

            });

            atomicThreads[i].start();
        }

        for (Thread thread : atomicThreads) {
            thread.join();
        }

        // ---------------- RESULTS ----------------
        int expected = THREADS * INCREMENTS;

        System.out.println("Expected Count      : " + expected);
        System.out.println("Unsafe Counter      : " + unsafeCounter.getCount());
        System.out.println("Synchronized Counter: " + safeCounter.getCount());
        System.out.println("Atomic Counter      : " + atomicCounter.getCount());
    }
}
