class CQueue {

    private Deque<Integer> s1;
    private Deque<Integer> s2;
    public CQueue() {
        s1 = new ArrayDeque<>();
        s2 = new ArrayDeque<>();
    }
    
    public void appendTail(int value) {
        s1.push(value);
        if (s2.isEmpty()) {
            move();
        }
    }
    
    public int deleteHead() {
        if (s2.isEmpty()) {
            move();
        }
        return s2.isEmpty() ? -1 : s2.pop();
    }

    private void move() {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */