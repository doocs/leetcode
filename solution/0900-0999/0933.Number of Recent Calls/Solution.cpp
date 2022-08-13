class RecentCounter {
public:
    deque<int> q;

    RecentCounter() {
    }

    int ping(int t) {
        q.push_back(t);
        while (q.front() < t - 3000) {
            q.pop_front();
        }
        return q.size();
    }
};

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter* obj = new RecentCounter();
 * int param_1 = obj->ping(t);
 */