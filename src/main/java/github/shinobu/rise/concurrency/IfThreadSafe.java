package github.shinobu.rise.concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Shinobu
 * @since 8/28/2019
 */
public class IfThreadSafe {

    private static ConcurrentHashMap<String, Person> chm = new ConcurrentHashMap<>();

    private static CountDownLatch c = new CountDownLatch(1);

    private static volatile Thread tt1;
    private static volatile Thread tt2;



    public static void main(String[] args) throws InterruptedException {
        chm.put("test", new Person());
        Thread t1, t2;
        tt1 = t1 = new Thread(() -> {
            Person p = chm.get("test");
            while (true) {
                LockSupport.park();
                p.age += 1;
                LockSupport.unpark(tt2);
            }
        });

        tt2 = t2 = new Thread(() -> {
            Person p = chm.get("test");
            while (true) {
                int age = p.age;
                LockSupport.unpark(tt1);
                LockSupport.park();
                if (age == p.age) {
                    System.out.println("..." + age + " | " + p.age);
                }
            }
        });

        t1.start();
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(3));
        t2.start();
        LockSupport.parkNanos(TimeUnit.MINUTES.toNanos(1));
    }


}

class Person {
    int age;
}
