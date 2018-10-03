class KthLargest {

    PriorityQueue<Integer> queue;

    private int size = 0;

    public KthLargest(int k, int[] nums) {
        size = k;
        queue = new PriorityQueue<>(k, Integer::compareTo);
        int gap = nums.length - k;
        if (gap > 0) {
            for (int i = 0; i < k; ++i) {
                queue.offer(nums[i]);
            }
            for (int i = k; i < nums.length; ++i) {
                add(nums[i]);
            }
        } else {
            for (int i = 0; i < nums.length; ++i) {
                queue.offer(nums[i]);
            }
        }
    }

    public int add(int val) {
        if (queue.size() < size) {
            queue.offer(val);
            return queue.peek();
        }

        if (queue.peek() >= val) {
            return queue.peek();
        }

        queue.poll();
        queue.offer(val);
        return queue.peek();

    }
}

/**
 * Your KthLargest object will be instantiated and called as such: 
 * KthLargest obj = new KthLargest(k, nums); 
 * int param_1 = obj.add(val);
 */