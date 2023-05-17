#include <semaphore.h>

class Foo {
private:
    sem_t a, b, c;

public:
    Foo() {
        sem_init(&a, 0, 1);
        sem_init(&b, 0, 0);
        sem_init(&c, 0, 0);
    }

    void first(function<void()> printFirst) {
        sem_wait(&a);
        // printFirst() outputs "first". Do not change or remove this line.
        printFirst();
        sem_post(&b);
    }

    void second(function<void()> printSecond) {
        sem_wait(&b);
        // printSecond() outputs "second". Do not change or remove this line.
        printSecond();
        sem_post(&c);
    }

    void third(function<void()> printThird) {
        sem_wait(&c);
        // printThird() outputs "third". Do not change or remove this line.
        printThird();
        sem_post(&a);
    }
};