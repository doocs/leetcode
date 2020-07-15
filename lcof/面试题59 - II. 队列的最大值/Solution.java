class MaxQueue {

    private Queue<Integer> p = new ArrayDeque<>();
    private Deque<Integer> q = new ArrayDeque<>();

    public MaxQueue() {

    }
    
    public int max_value() {
        return q.isEmpty() ? -1 : q.peekFirst();
    }
    
    public void push_back(int value) {
        while (!q.isEmpty() && q.peekLast() < value) {
            q.pollLast();
        }
        q.addLast(value);
        p.add(value);
    }
    
    public int pop_front() {
        if (p.isEmpty()) {
            return -1;
        }
        int res = p.poll();
        if (res == q.peekFirst()) {
            q.pollFirst();
        }
        return res;
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */