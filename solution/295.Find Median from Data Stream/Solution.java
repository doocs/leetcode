class MedianFinder {

    private PriorityQueue<Integer> bigRoot;
    private PriorityQueue<Integer> smallRoot;

    /** initialize your data structure here. */
    public MedianFinder() {
        bigRoot = new PriorityQueue<>(Comparator.reverseOrder());
        smallRoot = new PriorityQueue<>(Integer::compareTo);
    }

    public void addNum(int num) {
        if (bigRoot.isEmpty() || bigRoot.peek() > num) {
            bigRoot.add(num);
        } else {
            smallRoot.add(num);
        }

        int size1 = bigRoot.size();
        int size2 = smallRoot.size();
        if (size1 - size2 > 1) {
            smallRoot.add(bigRoot.poll());
        } else if (size2 - size1 > 1) {
            bigRoot.add(smallRoot.poll());
        }
    }

    public double findMedian() {
        int size1 = bigRoot.size();
        int size2 = smallRoot.size();

        return size1 == size2 ? (bigRoot.peek() + smallRoot.peek()) * 1.0 / 2
                : (size1 > size2 ? bigRoot.peek() : smallRoot.peek());
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder(); obj.addNum(num); double param_2 =
 * obj.findMedian();
 */