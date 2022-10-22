class StackOfPlates {
public:
    StackOfPlates(int cap) {
        this->cap = cap;
    }

    void push(int val) {
        if (!cap) return;
        if (stk.empty() || stk[stk.size() - 1].size() >= cap) stk.emplace_back(stack<int>());
        stk[stk.size() - 1].push(val);
    }

    int pop() {
        return popAt(stk.size() - 1);
    }

    int popAt(int index) {
        int ans = -1;
        if (index >= 0 && index < stk.size()) {
            ans = stk[index].top();
            stk[index].pop();
            if (stk[index].empty()) {
                stk.erase(stk.begin() + index);
            }
        }
        return ans;
    }

private:
    vector<stack<int>> stk;
    int cap;
};

/**
 * Your StackOfPlates object will be instantiated and called as such:
 * StackOfPlates* obj = new StackOfPlates(cap);
 * obj->push(val);
 * int param_2 = obj->pop();
 * int param_3 = obj->popAt(index);
 */