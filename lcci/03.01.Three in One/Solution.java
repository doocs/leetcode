class TripleInOne {
    private int[] s;
    private int capacity;

    public TripleInOne(int stackSize) {
        s = new int[stackSize * 3 + 3];
        capacity = stackSize;
    }

    public void push(int stackNum, int value) {
        if (s[stackNum + 3 * capacity] < capacity) {
            s[s[stackNum + 3 * capacity] * 3 + stackNum] = value;
            ++s[stackNum + 3 * capacity];
        }
    }

    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            return -1;
        }
        --s[stackNum + 3 * capacity];
        return s[s[stackNum + 3 * capacity] * 3 + stackNum];
    }

    public int peek(int stackNum) {
        return isEmpty(stackNum) ? -1 : s[(s[stackNum + 3 * capacity] - 1) * 3 + stackNum];
    }

    public boolean isEmpty(int stackNum) {
        return s[stackNum + 3 * capacity] == 0;
    }
}

/**
 * Your TripleInOne object will be instantiated and called as such: TripleInOne
 * obj = new TripleInOne(stackSize); obj.push(stackNum,value); int param_2 =
 * obj.pop(stackNum); int param_3 = obj.peek(stackNum); boolean param_4 =
 * obj.isEmpty(stackNum);
 */