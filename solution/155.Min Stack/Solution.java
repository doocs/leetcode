class MinStack {
    
    private Stack<Integer> stack;
    private Stack<Integer> help;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        help = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        help.push(help.isEmpty() || help.peek() >= x ? x : help.peek());
    }
    
    public void pop() {
        stack.pop();
        help.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return help.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */