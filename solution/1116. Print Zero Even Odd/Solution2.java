class ZeroEvenOdd {

    private int n;
    private volatile int num = 0;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i ++) {
            synchronized (this) {
                while ((num & 1) != 0) {
                    this.wait();
                }
                printNumber.accept(0);
                num ++;
                this.notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            synchronized (this) {
                while ((num & 1) == 0 || (num % 4) != 3) {
                    this.wait();
                }
                printNumber.accept(i);
                num ++;
                this.notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            synchronized (this) {
                while ((num & 1) == 0 || (num % 4) != 1) {
                    this.wait();
                }
                printNumber.accept(i);
                num ++;
                this.notifyAll();
            }
        }
    }
}