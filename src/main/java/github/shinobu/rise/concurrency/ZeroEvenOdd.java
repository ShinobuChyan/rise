package github.shinobu.rise.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;
import java.util.function.IntConsumer;

/**
 * leetcode.1116: Print Zero Even Odd
 * env: java 8
 *
 * @author Shinobu
 * @since 8/20/2019
 */
public class ZeroEvenOdd {

    /**
     * test case
     */
    public static void main(String[] args) {
        System.out.println(1);
        ZeroEvenOdd o = new ZeroEvenOdd(6);
        Thread t1 = new Thread(() -> {
            try {
                o.zero(System.out::println);
                System.out.println("t1 exit" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                o.odd(n -> System.out.println("odd: " + n));
                System.out.println("t2 exit" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                o.even(n -> System.out.println("even: " + n));
                System.out.println("t3 exit" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(2);

        t1.start();
        t2.start();
        t3.start();
        System.out.println(System.currentTimeMillis());
        LockSupport.parkNanos(TimeUnit.MINUTES.toNanos(10));
    }

    private volatile int i;
    private final int max;
    private CountDownLatch c = new CountDownLatch(2);
    private volatile Thread zt = null;
    private volatile Thread ot = null;
    private volatile Thread et = null;

    public ZeroEvenOdd(int n) {
        this.max = n << 1;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        zt = Thread.currentThread();
        int ii;
        Thread ot = null;
        Thread et = null;
        while ((ii = i) < max) {
            if ((ii & 1) == 0) {
                printNumber.accept(0);
                i = ++ii;
                if (ii == 1) {
                    c.await();
                    LockSupport.unpark(ot == null ? ot = this.ot : ot);
                } else {
                    int t = ii + 1;
                    if (((t >> 1) & 1) == 0) {
                        LockSupport.unpark(et == null ? et = this.et : et);
                    } else {
                        LockSupport.unpark(ot);
                    }
                }
            }
            LockSupport.park();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        et = Thread.currentThread();
        int ii;
        c.countDown();
        LockSupport.park();
        final Thread zt = this.zt;
        while ((ii = i) < max) {
            printNumber.accept((ii + 1) >> 1);
            i = ++ii;
            LockSupport.unpark(zt);
            if (ii < max) {
                LockSupport.park();
            } else {
                LockSupport.unpark(ot);
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        ot = Thread.currentThread();
        int ii;
        c.countDown();
        LockSupport.park();
        final Thread zt = this.zt;
        while ((ii = i) < max) {
            printNumber.accept((ii + 1) >> 1);
            i = ++ii;
            LockSupport.unpark(zt);
            if (ii < max) {
                LockSupport.park();
            } else {
                LockSupport.unpark(et);
            }
        }
    }

//    // printNumber.accept(x) outputs "x", where x is an integer.
//    public void zero(IntConsumer printNumber) throws InterruptedException {
//        int ii;
//        while ((ii = i) < max) {
//            if ((ii & 1) == 0) {
//                printNumber.accept(0);
//                i = ii + 1;
//            }
//        }
//    }
//
//    public void even(IntConsumer printNumber) throws InterruptedException {
//        int ii;
//        while ((ii = i) < max) {
//            if ((ii & 1) != 0) {
//                if (ii == 1) {
//                    break;
//                }
//                int t = ii + 1;
//                boolean b = true;
//                while ((t >>= 1) > 1) {
//                    if ((t & 1) == 1) {
//                        b = false;
//                        break;
//                    }
//                }
//                if (b) {
//                    printNumber.accept((ii += 1) / 2);
//                    i = ii;
//                }
//            }
//        }
//    }
//
//    public void odd(IntConsumer printNumber) throws InterruptedException {
//        int ii;
//        while ((ii = i) < max) {
//            if ((ii & 1) != 0) {
//                if (ii == 1) {
//                    printNumber.accept(1);
//                    i = 2;
//                } else {
//                    int t = ii + 1;
//                    while ((t >>= 1) > 1) {
//                        if ((t & 1) == 1) {
//                            printNumber.accept(++ii / 2);
//                            i = ii;
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//    }


}
