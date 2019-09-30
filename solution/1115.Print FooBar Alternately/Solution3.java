import java.util.concurrent.CountDownLatch;


class FooBar {

    private int n;
    private CountDownLatch countDownLatchA;
    private CountDownLatch countDownLatchB;

    public FooBar(int n) {
        this.n = n;
        this.countDownLatchA = new CountDownLatch(1);
        this.countDownLatchB = new CountDownLatch(1);
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            countDownLatchA.await();
            printFoo.run();
            countDownLatchB.countDown();
            countDownLatchA = new CountDownLatch(1);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            countDownLatchA.countDown();
            countDownLatchB.await();
            printBar.run();
            countDownLatchB = new CountDownLatch(1);
        }
    }
}