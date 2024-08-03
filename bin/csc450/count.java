package csc450;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class count {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new CountUp());
        Thread thread2 = new Thread(new CountDown());

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class CountUp implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 20; ++i) {
                lock.lock();
                try {
                    System.out.println("Count up: " + i);
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class CountDown implements Runnable {
        @Override
        public void run() {
            for (int i = 20; i >= 0; --i) {
                lock.lock();
                try {
                    System.out.println("Count down: " + i);
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}