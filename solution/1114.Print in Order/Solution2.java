import java.util.concurrent.CountDownLatch;

class Foo {
    private CountDownLatch one = new CountDownLatch(1);
    private CountDownLatch two = new CountDownLatch(1);
    public Foo() {}

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        one.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        one.await();
        printSecond.run();
        two.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        two.await();
        printThird.run();
    }
}