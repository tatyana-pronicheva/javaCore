package demo.gb.lessons.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCounter {
    private int count=0;
    Lock lock = new ReentrantLock();

    public int getCount() {
        return count;
    }

    public void increment(){
        lock.lock();
        count++;
        lock.unlock();
    }
    public void decrement(){
        lock.lock();
        count--;
        lock.unlock();
    }

}

