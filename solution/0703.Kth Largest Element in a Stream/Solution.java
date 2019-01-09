class KthLargest {

    private PriorityQueue<Integer> minHeap;
    private int size;

    public KthLargest(int k, int[] nums) {
        minHeap = new PriorityQueue<>(k);
        size = k;
        for (int e : nums) {
            add(e);
        }
    }

    public int add(int val) {
        if (minHeap.size() < size) {
            minHeap.add(val);
        } else {
            if (minHeap.peek() < val) {
                minHeap.poll();
                minHeap.add(val);
            }
        }
        return minHeap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such: KthLargest
 * obj = new KthLargest(k, nums); int param_1 = obj.add(val);
 */