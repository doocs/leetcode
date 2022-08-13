class MinStack {
private:
    stack<int> stk;
    stack<int> minStk;

public:
    /** initialize your data structure here. */
    MinStack() = default;

    void push(int x) {
        if (minStk.empty() || minStk.top() >= x) {
            minStk.push(x);
        }
        stk.push(x);
    }

    void pop() {
        int val = stk.top();
        stk.pop();
        if (val == minStk.top()) {
            minStk.pop();
        }
    }

    int top() {
        return stk.top();
    }

    int getMin() {
        return minStk.top();
    }
};

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack* obj = new MinStack();
 * obj->push(x);
 * obj->pop();
 * int param_3 = obj->top();
 * int param_4 = obj->getMin();
 */