import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CreateProcessDemo {
    public static void main(String[] args) {
        // 创建 Condition 对象
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition(); // lock 下可创建多个 Condition
        // 加锁

        new Thread(()->{
            try {
                if (lock.tryLock( 10 , TimeUnit.SECONDS) ) {
                    // 业务方法......
                    // 1.进入等待状态
                    for (int i = 0; i < 10; i++) {
                        System.out.println("1马上睡着了");
                    }
                    // 释放锁睡觉
                    System.out.println("1释放锁睡觉");
                    condition.await();
                    System.out.println("1睡着被唤醒了");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } , "Thread1").start();

        new Thread(()->{
            // 业务方法......
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 2.唤醒操作
            condition.signal();
            System.out.println("2睡着被唤醒了");
        } , "Thread2").start();
    }
}
