class ZeroEvenOdd {
private:
    int n;
    int flag;
    mutex m1, m2, m3;

public:
    ZeroEvenOdd(int n) {
        this->n = n;
        flag = 1; //奇偶判断
        m1.lock();
        m2.lock();
        m3.lock();
    }

    // printNumber(x) outputs "x", where x is an integer.
    void zero(function<void(int)> printNumber) {
        m3.unlock();
        for (int i = 0; i < n; i++) {
            m3.lock();
            printNumber(0);
            if (flag == 1)
                flag = 0, m2.unlock();
            else
                flag = 1, m1.unlock();
        }
    }

    void odd(function<void(int)> printNumber) { //输出奇数
        for (int i = 1; i <= n; i += 2) {
            m2.lock();
            printNumber(i);
            m3.unlock();
        }
    }

    void even(function<void(int)> printNumber) { //输出偶数
        for (int i = 2; i <= n; i += 2) {
            m1.lock();
            printNumber(i);
            m3.unlock();
        }
    }
};