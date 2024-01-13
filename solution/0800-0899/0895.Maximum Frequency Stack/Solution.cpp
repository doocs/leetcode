class FreqStack {
public:
    FreqStack() {
    }

    void push(int val) {
        ++cnt[val];
        q.emplace(cnt[val], ++ts, val);
    }

    int pop() {
        auto [a, b, val] = q.top();
        q.pop();
        --cnt[val];
        return val;
    }

private:
    unordered_map<int, int> cnt;
    priority_queue<tuple<int, int, int>> q;
    int ts = 0;
};

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack* obj = new FreqStack();
 * obj->push(val);
 * int param_2 = obj->pop();
 */