class DiningPhilosophers {
public:
    using Act = function<void()>;

    void wantsToEat(int philosopher, Act pickLeftFork, Act pickRightFork, Act eat, Act putLeftFork, Act putRightFork) {
        /* 这一题实际上是用到了C++17中的scoped_lock知识。
                   作用是传入scoped_lock(mtx1, mtx2)两个锁，然后在作用范围内，依次顺序上锁mtx1和mtx2；然后在作用范围结束时，再反续解锁mtx2和mtx1。
                   从而保证了philosopher1有动作的时候，philosopher2无法操作；但是philosopher3和philosopher4不受影响 */
        std::scoped_lock lock(mutexes_[philosopher], mutexes_[philosopher >= 4 ? 0 : philosopher + 1]);
        pickLeftFork();
        pickRightFork();
        eat();
        putLeftFork();
        putRightFork();
    }

private:
    vector<mutex> mutexes_ = vector<mutex>(5);
};