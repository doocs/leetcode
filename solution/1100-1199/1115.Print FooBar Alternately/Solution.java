import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class FooBar {

    private int n;
    private Lock lock;
    private volatile Boolean flag;

    public FooBar(int n) {
        this.n = n;
        this.flag = true;
        this.lock = new ReentrantLock(true);
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n;) {
            lock.lock();
            if (flag) {
                printFoo.run();
                flag = false;
                i++;
            }
            lock.unlock();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n;) {
            lock.lock();
            if (!flag) {
                printBar.run();
                flag = true;
                i++;
            }
            lock.unlock();
        }
    }
}