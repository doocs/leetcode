#include <semaphore.h>

class BoundedBlockingQueue {
public:
    BoundedBlockingQueue(int capacity) {
        sem_init(&s1, 0, capacity);
        sem_init(&s2, 0, 0);
    }

    void enqueue(int element) {
        sem_wait(&s1);
        q.push(element);
        sem_post(&s2);
    }

    int dequeue() {
        sem_wait(&s2);
        int ans = q.front();
        q.pop();
        sem_post(&s1);
        return ans;
    }

    int size() {
        return q.size();
    }

private:
    queue<int> q;
    sem_t s1, s2;
};