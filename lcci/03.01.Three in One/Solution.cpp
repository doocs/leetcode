class TripleInOne {
public:
    TripleInOne(int stackSize) {
        cap = stackSize;
        stk.resize(cap * 3 + 3);
    }
    
    void push(int stackNum, int value) {
        if (stk[cap * 3 + stackNum] < cap) {
            stk[cap * stackNum + stk[cap * 3 + stackNum]] = value;
            ++stk[cap * 3 + stackNum];
        }
    }
    
    int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            return -1;
        }
        --stk[cap * 3 + stackNum];
        return stk[cap * stackNum + stk[cap * 3 + stackNum]];
    }
    
    int peek(int stackNum) {
        return isEmpty(stackNum) ? -1 : stk[cap * stackNum + stk[cap * 3 + stackNum] - 1];
    }
    
    bool isEmpty(int stackNum) {
        return stk[cap * 3 + stackNum] == 0;
    }

private:
    int cap;
    vector<int> stk;
};

/**
 * Your TripleInOne object will be instantiated and called as such:
 * TripleInOne* obj = new TripleInOne(stackSize);
 * obj->push(stackNum,value);
 * int param_2 = obj->pop(stackNum);
 * int param_3 = obj->peek(stackNum);
 * bool param_4 = obj->isEmpty(stackNum);
 */