class MaxQueue {
public:
    MaxQueue() {
    }

    int max_value() {
        return q2.empty() ? -1 : q2.front();
    }

    void push_back(int value) {
        while (!q2.empty() && q2.back() < value) {
            q2.pop_back();
        }
        q1.push(value);
        q2.push_back(value);
    }

    int pop_front() {
        if (q1.empty()) {
            return -1;
        }
        int ans = q1.front();
        q1.pop();
        if (q2.front() == ans) {
            q2.pop_front();
        }
        return ans;
    }

private:
    queue<int> q1;
    deque<int> q2;
};

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue* obj = new MaxQueue();
 * int param_1 = obj->max_value();
 * obj->push_back(value);
 * int param_3 = obj->pop_front();
 */