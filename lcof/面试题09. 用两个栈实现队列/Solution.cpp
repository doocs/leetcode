class CQueue {
public:
    CQueue() {
    }

    void appendTail(int value) {
        stk1.push(value);
    }

    int deleteHead() {
        if (stk2.empty()) {
            while (!stk1.empty()) {
                stk2.push(stk1.top());
                stk1.pop();
            }
        }
        if (stk2.empty()) {
            return -1;
        }
        int ans = stk2.top();
        stk2.pop();
        return ans;
    }

private:
    stack<int> stk1, stk2;
};

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue* obj = new CQueue();
 * obj->appendTail(value);
 * int param_2 = obj->deleteHead();
 */