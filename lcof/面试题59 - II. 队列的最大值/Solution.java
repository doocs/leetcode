class MaxQueue {
    private Deque<Integer> q1 = new ArrayDeque<>();
    private Deque<Integer> q2 = new ArrayDeque<>();

    public MaxQueue() {
    }

    public int max_value() {
        return q2.isEmpty() ? -1 : q2.peek();
    }

    public void push_back(int value) {
        while (!q2.isEmpty() && q2.peekLast() < value) {
            q2.pollLast();
        }
        q1.offer(value);
        q2.offer(value);
    }

    public int pop_front() {
        if (q1.isEmpty()) {
            return -1;
        }
        int ans = q1.poll();
        if (q2.peek() == ans) {
            q2.poll();
        }
        return ans;
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */