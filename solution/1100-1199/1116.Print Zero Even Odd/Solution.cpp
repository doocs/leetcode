#include <semaphore.h>

class ZeroEvenOdd {
private:
    int n;
    sem_t z, e, o;

public:
    ZeroEvenOdd(int n) {
        this->n = n;
        sem_init(&z, 0, 1);
        sem_init(&e, 0, 0);
        sem_init(&o, 0, 0);
    }

    // printNumber(x) outputs "x", where x is an integer.
    void zero(function<void(int)> printNumber) {
        for (int i = 0; i < n; ++i) {
            sem_wait(&z);
            printNumber(0);
            if (i % 2 == 0) {
                sem_post(&o);
            } else {
                sem_post(&e);
            }
        }
    }

    void even(function<void(int)> printNumber) {
        for (int i = 2; i <= n; i += 2) {
            sem_wait(&e);
            printNumber(i);
            sem_post(&z);
        }
    }

    void odd(function<void(int)> printNumber) {
        for (int i = 1; i <= n; i += 2) {
            sem_wait(&o);
            printNumber(i);
            sem_post(&z);
        }
    }
};