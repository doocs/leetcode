class CQueue {
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    public CQueue() {

    }
    
    public void appendTail(int value) {
        s1.push(value);
        check();
    }
    
    public int deleteHead() {
        check();
        return s2.empty() ? -1 : s2.pop();
    }

    private void check() {
        if (s2.empty()) {
            while (!s1.empty()) {
                s2.push(s1.pop());
            }
        }
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */