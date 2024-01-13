class FizzBuzz {
private:
    std::mutex mtx;
    atomic<int> index;
    int n;

    // 这里主要运用到了C++11中的RAII锁(lock_guard)的知识。
    // 需要强调的一点是，在进入循环后，要时刻不忘加入index <= n的逻辑
public:
    FizzBuzz(int n) {
        this->n = n;
        index = 1;
    }

    void fizz(function<void()> printFizz) {
        while (index <= n) {
            std::lock_guard<std::mutex> lk(mtx);
            if (0 == index % 3 && 0 != index % 5 && index <= n) {
                printFizz();
                index++;
            }
        }
    }

    void buzz(function<void()> printBuzz) {
        while (index <= n) {
            std::lock_guard<std::mutex> lk(mtx);
            if (0 == index % 5 && 0 != index % 3 && index <= n) {
                printBuzz();
                index++;
            }
        }
    }

    void fizzbuzz(function<void()> printFizzBuzz) {
        while (index <= n) {
            std::lock_guard<std::mutex> lk(mtx);
            if (0 == index % 15 && index <= n) {
                printFizzBuzz();
                index++;
            }
        }
    }

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