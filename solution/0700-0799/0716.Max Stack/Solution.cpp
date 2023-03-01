class MaxStack {
public:
    MaxStack() {
    }

    void push(int x) {
        stk.push_back(x);
        tm.insert({x, --stk.end()});
    }

    int pop() {
        auto it = --stk.end();
        int ans = *it;
        auto mit = --tm.upper_bound(ans);
        tm.erase(mit);
        stk.erase(it);
        return ans;
    }

    int top() {
        return stk.back();
    }

    int peekMax() {
        return tm.rbegin()->first;
    }

    int popMax() {
        auto mit = --tm.end();
        auto it = mit->second;
        int ans = *it;
        tm.erase(mit);
        stk.erase(it);
        return ans;
    }

private:
    multimap<int, list<int>::iterator> tm;
    list<int> stk;
};

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack* obj = new MaxStack();
 * obj->push(x);
 * int param_2 = obj->pop();
 * int param_3 = obj->top();
 * int param_4 = obj->peekMax();
 * int param_5 = obj->popMax();
 */