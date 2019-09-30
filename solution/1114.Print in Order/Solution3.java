class Foo {

    private volatile int index = 1;
    public Foo() {}

    public void first(Runnable printFirst) throws InterruptedException {
        while (index != 1) {}
        printFirst.run();
        index = 2;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (index != 2) {}
        printSecond.run();
        index = 3;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (index != 3) {}
        printThird.run();
    }
}