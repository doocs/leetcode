class SortedStack {
    private Stack<Integer> s;
    public SortedStack() {
        s = new Stack<>();
    }

    public void push(int val) {
        Stack<Integer> t = new Stack<>();
        while (!isEmpty() && s.peek() < val) {
            t.push(s.pop());
        }
        s.push(val);
        while (!t.isEmpty()) {
            s.push(t.pop());
        }
    }

    public void pop() {
        if (!isEmpty()) {
            s.pop();
        }
    }

    public int peek() {
        return isEmpty() ? -1 : s.peek();
    }

    public boolean isEmpty() {
        return s.isEmpty();
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