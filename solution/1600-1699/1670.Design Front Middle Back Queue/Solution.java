class FrontMiddleBackQueue {
    private Deque<Integer> left;
    private Deque<Integer> right;

    public FrontMiddleBackQueue() {
        left = new LinkedList<>();
        right = new LinkedList<>();
    }
    
    public void pushFront(int val) {
        left.offerFirst(val);
        rebalance();
    }
    
    public void pushMiddle(int val) {
        left.offerLast(val);
        rebalance();
    }
    
    public void pushBack(int val) {
        right.offerLast(val);
        rebalance();
    }
    
    public int popFront() {
        if (empty()) {
            return -1;
        }
        int val = left.isEmpty() ? right.pollFirst() : left.pollFirst();
        rebalance();
        return val;
    }
    
    public int popMiddle() {
        if (empty()) {
            return -1;
        }
        int val = left.size() >= right.size() ? left.pollLast() : right.pollFirst();
        rebalance();
        return val;
    }
    
    public int popBack() {
        if (empty()) {
            return -1;
        }
        int val = right.pollLast();
        rebalance();
        return val;
    }

    private boolean empty() {
        return left.isEmpty() && right.isEmpty();
    }

    private void rebalance() {
        while (left.size() > right.size()) {
            right.offerFirst(left.pollLast());
        }
        while (right.size() - left.size() > 1) {
            left.offerLast(right.pollFirst());
        }
    }
}

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
 * obj.pushFront(val);
 * obj.pushMiddle(val);
 * obj.pushBack(val);
 * int param_4 = obj.popFront();
 * int param_5 = obj.popMiddle();
 * int param_6 = obj.popBack();
 */