class CustomStack {
public:
    vector<int> s;
    int t;

    CustomStack(int maxSize) {
        s.resize(maxSize);
        t = 0;
    }

    void push(int x) {
        if (t < s.size()) s[t++] = x;
    }

    int pop() {
        return t == 0 ? -1 : s[--t];
    }

    void increment(int k, int val) {
        for (int i = 0; i < min(k, t); ++i) s[i] += val;
    }
};

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack* obj = new CustomStack(maxSize);
 * obj->push(x);
 * int param_2 = obj->pop();
 * obj->increment(k,val);
 */