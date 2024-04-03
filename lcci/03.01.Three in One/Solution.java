class TripleInOne {
    private int cap;
    private int[] stk;

    public TripleInOne(int stackSize) {
        cap = stackSize;
        stk = new int[cap * 3 + 3];
    }
    
    public void push(int stackNum, int value) {
        if (stk[cap * 3 + stackNum] < cap) {
            stk[cap * stackNum + stk[cap * 3 + stackNum]] = value;
            ++stk[cap * 3 + stackNum];
        }
    }
    
    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            return -1;
        }
        --stk[cap * 3 + stackNum];
        return stk[cap * stackNum + stk[cap * 3 + stackNum]];
    }
    
    public int peek(int stackNum) {
        return isEmpty(stackNum) ? -1 : stk[cap * stackNum + stk[cap * 3 + stackNum] - 1];
    }
    
    public boolean isEmpty(int stackNum) {
        return stk[cap * 3 + stackNum] == 0;
    }
}

/**
 * Your TripleInOne object will be instantiated and called as such:
 * TripleInOne obj = new TripleInOne(stackSize);
 * obj.push(stackNum,value);
 * int param_2 = obj.pop(stackNum);
 * int param_3 = obj.peek(stackNum);
 * boolean param_4 = obj.isEmpty(stackNum);
 */