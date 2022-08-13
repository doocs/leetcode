class FizzBuzz {
private:
    std::mutex mtx;
    atomic<int> index;
    int n;

public:
    FizzBuzz(int n) {
        this->n = n;
        index = 1;
    }

    // printFizz() outputs "fizz".
    void fizz(function<void()> printFizz) {
        while (index <= n) {
            std::lock_guard<std::mutex> lk(mtx);
            if (0 == index % 3 && 0 != index % 5 && index <= n) {
                printFizz();
                index++;
            }
        }
    }

    // printBuzz() outputs "buzz".
    void buzz(function<void()> printBuzz) {
        while (index <= n) {
            std::lock_guard<std::mutex> lk(mtx);
            if (0 == index % 5 && 0 != index % 3 && index <= n) {
                printBuzz();
                index++;
            }
        }
    }

    // printFizzBuzz() outputs "fizzbuzz".
    void fizzbuzz(function<void()> printFizzBuzz) {
        while (index <= n) {
            std::lock_guard<std::mutex> lk(mtx);
            if (0 == index % 15 && index <= n) {
                printFizzBuzz();
                index++;
            }
        }
    }

    // printNumber(x) outputs "x", where x is an integer.
    void number(function<void(int)> printNumber) {
        while (index <= n) {
            std::lock_guard<std::mutex> lk(mtx);
            if (0 != index % 3 && 0 != index % 5 && index <= n) {
                printNumber(index);
                index++;
            }
        }
    }
};