class MinStack {
private:
    stack<int> a, b;

public:
    /** initialize your data structure here. */
    MinStack() {
    }

    void push(int x) {
        a.push(x);
        if (b.empty() || x <= b.top()) {
            b.push(x);
        }
    }

    void pop() {
        if (a.top() == b.top()) {
            b.pop();
        }
        a.pop();
    }

    int top() {
        return a.top();
    }

    int min() {
        return b.top();
    }
};
