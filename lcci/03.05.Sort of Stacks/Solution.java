class SortedStack {
    private Deque<Integer> stk = new ArrayDeque<>();

    public SortedStack() {
    }

    public void push(int val) {
        Deque<Integer> t = new ArrayDeque<>();
        while (!stk.isEmpty() && stk.peek() < val) {
            t.push(stk.pop());
        }
        stk.push(val);
        while (!t.isEmpty()) {
            stk.push(t.pop());
        }
    }

    public void pop() {
        if (!isEmpty()) {
            stk.pop();
        }
    }

    public int peek() {
        return isEmpty() ? -1 : stk.peek();
    }

    public boolean isEmpty() {
        return stk.isEmpty();
    }
}

/**
 * Your SortedStack object will be instantiated and called as such:
 * SortedStack obj = new SortedStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.isEmpty();
 */