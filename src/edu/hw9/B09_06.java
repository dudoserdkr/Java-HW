package edu.hw9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class B09_06 {

    private static final int N = 3;
    private static final int SPECTATORS = 100;

    private static final long T1 = 2L;
    private static final long T2 = 20L;
    private static final long T3 = 5L;

    public static void main(String[] args) throws InterruptedException {
        Semaphore turnstiles = new Semaphore(N);
        AtomicInteger inTimeCount = new AtomicInteger(0);
        Random random = new Random();

        long start = System.currentTimeMillis();
        long matchStart = start + T1 + T2;
        long openTime = matchStart - T3;

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < SPECTATORS; i++) {
            int arrivalDelay = random.nextInt((int) T2 + 1);

            Thread spectator = new Thread(() -> {
                try {
                    sleepUntil(start + arrivalDelay);

                    long now = System.currentTimeMillis();
                    if (now < openTime) {
                        sleepUntil(openTime);
                    }

                    turnstiles.acquire();

                    long enterTime = System.currentTimeMillis();
                    long exitTime = enterTime + T1;
                    boolean inTime = exitTime <= matchStart;

                    Thread.sleep(T1);

                    if (inTime) {
                        inTimeCount.incrementAndGet();
                    }

                    turnstiles.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            spectator.setName("Spectator-" + i);
            spectator.start();
            threads.add(spectator);
        }

        for (Thread spectator : threads) {
            spectator.join();
        }

        int inTime = inTimeCount.get();
        int late = SPECTATORS - inTime;

        System.out.println("Total spectators: " + SPECTATORS);
        System.out.println("Spectators in time: " + inTime);
        System.out.println("Spectators too late: " + late);
    }

    private static void sleepUntil(long targetTime) throws InterruptedException {
        long delay = targetTime - System.currentTimeMillis();
        if (delay > 0) {
            Thread.sleep(delay);
        }
    }
}
