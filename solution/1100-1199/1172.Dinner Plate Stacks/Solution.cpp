class DinnerPlates {
public:
    DinnerPlates(int capacity) {
        this->capacity = capacity;
    }

    void push(int val) {
        if (notFull.empty()) {
            stacks.emplace_back(stack<int>());
            stacks.back().push(val);
            if (capacity > 1) {
                notFull.insert(stacks.size() - 1);
            }
        } else {
            int index = *notFull.begin();
            stacks[index].push(val);
            if (stacks[index].size() == capacity) {
                notFull.erase(index);
            }
        }
    }

    int pop() {
        return popAtStack(stacks.size() - 1);
    }

    int popAtStack(int index) {
        if (index < 0 || index >= stacks.size() || stacks[index].empty()) {
            return -1;
        }
        int val = stacks[index].top();
        stacks[index].pop();
        if (index == stacks.size() - 1 && stacks[index].empty()) {
            while (!stacks.empty() && stacks.back().empty()) {
                notFull.erase(stacks.size() - 1);
                stacks.pop_back();
            }
        } else {
            notFull.insert(index);
        }
        return val;
    }

private:
    int capacity;
    vector<stack<int>> stacks;
    set<int> notFull;
};

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * DinnerPlates* obj = new DinnerPlates(capacity);
 * obj->push(val);
 * int param_2 = obj->pop();
 * int param_3 = obj->popAtStack(index);
 */