class MinStack {
    private Deque<Integer> s;
    private Deque<Integer> mins;

    /** initialize your data structure here. */
    public MinStack() {
        s = new ArrayDeque<>();
        mins = new ArrayDeque<>();
        mins.push(Integer.MAX_VALUE);
    }
    
    public void push(int val) {
        s.push(val);
        mins.push(Math.min(mins.peek(), val));
    }
    
    public void pop() {
        s.pop();
        mins.pop();
    }
    
    public int top() {
        return s.peek();
    }
    
    public int min() {
        return mins.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */