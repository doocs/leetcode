class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for (int e : nums) {
            if (minHeap.size() < k) {
                minHeap.add(e);
            } else {
                if (minHeap.peek() < e) {
                    minHeap.poll();
                    minHeap.add(e);
                }
            }
            
        }
        return minHeap.peek();
    }
}