class SortedStack {
public:
    SortedStack() {
    }

    void push(int val) {
        stack<int> t;
        while (!stk.empty() && stk.top() < val) {
            t.push(stk.top());
            stk.pop();
        }
        stk.push(val);
        while (!t.empty()) {
            stk.push(t.top());
            t.pop();
        }
    }

    void pop() {
        if (!isEmpty()) {
            stk.pop();
        }
    }

    int peek() {
        return isEmpty() ? -1 : stk.top();
    }

    bool isEmpty() {
        return stk.empty();
    }

private:
    stack<int> stk;
};

/**
 * Your SortedStack object will be instantiated and called as such:
 * SortedStack* obj = new SortedStack();
 * obj->push(val);
 * obj->pop();
 * int param_3 = obj->peek();
 * bool param_4 = obj->isEmpty();
 */