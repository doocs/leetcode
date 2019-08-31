import java.util.concurrent.Semaphore;

class Foo {
    private Semaphore twoS = new Semaphore(0);
    private Semaphore threeS = new Semaphore(0);
    
    public Foo() {
        
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        twoS.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        twoS.acquire();
        printSecond.run();
        threeS.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        threeS.acquire();
        printThird.run();
    }
}