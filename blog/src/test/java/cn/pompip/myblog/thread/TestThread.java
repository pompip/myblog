package cn.pompip.myblog.thread;

import org.junit.Test;

public class TestThread {
    static volatile int num = 1;
    private final Object lock = new Object();

    @Test
    public void testThread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (!isInterrupted()) {
                        try {
                            lock.wait();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
//                            interrupt();
                        }
                        System.out.println("hello world num:" + num + "isInterrupted:" + isInterrupted() + " threadName:" + getName());
                    }
                }
            }
        };
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                super.run();

                synchronized (lock) {
                    while (!isInterrupted()) {
                        num++;
                        System.out.println("num: " + num);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (num % 10 == 0) {
                            yield();
                            lock.notify();
                            try {
                                lock.wait(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("thread isInterrrupt:" + thread.isInterrupted() + " threadName:" + thread.getName());
                        }

                    }


                }
            }
        };
        thread1.start();
        System.out.println("end1");
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");


    }
}
