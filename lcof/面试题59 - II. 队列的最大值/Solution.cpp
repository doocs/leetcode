class MaxQueue {
private:
    queue<int> q;
    deque<int> d;

public:
    MaxQueue() { }

    int max_value() {
        if (d.empty()) return -1;
        return d.front();
    }

    void push_back(int value) {
        while (!d.empty() && d.back() < value) d.pop_back();
        d.push_back(value);
        q.push(value);
    }

    int pop_front() {
        if (d.empty()) return -1;
        int retVal = q.front();
        q.pop();
        if (d.front() == retVal) d.pop_front();
        return retVal;
    }
};
