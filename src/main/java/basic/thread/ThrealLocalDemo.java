package basic.thread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class ThrealLocalDemo {

    private static ThreadLocal<Integer> seqNum = ThreadLocal.withInitial(() -> 0);

    private static final int HASH_INCREMENT = 0x61c88647;
    private static AtomicInteger nextHashCode = new AtomicInteger();


    public static void main(String[] args) {
        System.out.println(HASH_INCREMENT);
        System.out.println(nextHashCode.getAndAdd(HASH_INCREMENT));
        System.out.println(nextHashCode.get());
    }

    @Test
    public void testThreadLocal() {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + "---" + seqNum.get());
                seqNum.set(seqNum.get() + 1);
                System.out.println(threadName + "---" + seqNum.get());
            }).start();
        }
    }

}
