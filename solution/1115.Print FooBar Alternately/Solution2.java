import java.util.concurrent.Semaphore;


class FooBar {

    private int n;
    private Semaphore semaphoreA;
    private Semaphore semaphoreB;

    public FooBar(int n) {
        this.n = n;
        this.semaphoreA = new Semaphore(1);
        this.semaphoreB = new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            semaphoreA.acquire();
            printFoo.run();
            semaphoreB.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            semaphoreB.acquire();
            printBar.run();
            semaphoreA.release();
        }
    }
}