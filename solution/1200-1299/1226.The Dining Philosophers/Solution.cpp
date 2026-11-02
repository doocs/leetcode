class DiningPhilosophers {
public:
    using Act = function<void()>;

    void wantsToEat(int philosopher, Act pickLeftFork, Act pickRightFork, Act eat, Act putLeftFork, Act putRightFork) {
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