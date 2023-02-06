class MedianFinder {
    private PriorityQueue<Integer> q1 = new PriorityQueue<>();
    private PriorityQueue<Integer> q2 = new PriorityQueue<>((a, b) -> b - a);

    /** initialize your data structure here. */
    public MedianFinder() {
    }

    public void addNum(int num) {
        if (q1.size() > q2.size()) {
            q1.offer(num);
            q2.offer(q1.poll());
        } else {
            q2.offer(num);
            q1.offer(q2.poll());
        }
    }

    public double findMedian() {
        if (q1.size() > q2.size()) {
            return q1.peek();
        }
        return (q1.peek() + q2.peek()) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */