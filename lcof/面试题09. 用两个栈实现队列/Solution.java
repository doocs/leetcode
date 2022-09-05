class CQueue {
    private Deque<Integer> stk1 = new ArrayDeque<>();
    private Deque<Integer> stk2 = new ArrayDeque<>();

    public CQueue() {
    }

    public void appendTail(int value) {
        stk1.push(value);
    }

    public int deleteHead() {
        if (stk2.isEmpty()) {
            while (!stk1.isEmpty()) {
                stk2.push(stk1.pop());
            }
        }
        return stk2.isEmpty() ? -1 : stk2.pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */