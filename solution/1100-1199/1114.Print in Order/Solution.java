class Foo {
    private Semaphore a = new Semaphore(1);
    private Semaphore b = new Semaphore(0);
    private Semaphore c = new Semaphore(0);

    public Foo() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        a.acquire(1);
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        b.release(1);
    }

    public void second(Runnable printSecond) throws InterruptedException {
        b.acquire(1);
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        c.release(1);
    }

    public void third(Runnable printThird) throws InterruptedException {
        c.acquire(1);
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        a.release(1);
    }
}