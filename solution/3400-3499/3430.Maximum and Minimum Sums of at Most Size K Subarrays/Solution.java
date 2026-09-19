class Solution {
    public long minMaxSubarraySum(int[] nums, int k) {
        long total = 0, windowMax = 0, windowMin = 0;
        Deque<long[]> maxStack = new ArrayDeque<>();
        Deque<long[]> minStack = new ArrayDeque<>();
        for (int end = 0; end < nums.length; ++end) {
            int start = Math.max(0, end - k + 1);
            if (start > 0) {
                maxStack.peekFirst()[2]--;
                windowMax -= maxStack.peekFirst()[1];
                if (maxStack.peekFirst()[0] < start) {
                    maxStack.pollFirst();
                }
                minStack.peekFirst()[2]--;
                windowMin -= minStack.peekFirst()[1];
                if (minStack.peekFirst()[0] < start) {
                    minStack.pollFirst();
                }
            }
            long num = nums[end];
            long maxShares = 1;
            windowMax += num;
            while (!maxStack.isEmpty() && maxStack.peekLast()[1] <= num) {
                long prevNum = maxStack.peekLast()[1];
                long prevShares = maxStack.pollLast()[2];
                maxShares += prevShares;
                windowMax += (num - prevNum) * prevShares;
            }
            maxStack.addLast(new long[] {end, num, maxShares});
            long minShares = 1;
            windowMin += num;
            while (!minStack.isEmpty() && minStack.peekLast()[1] >= num) {
                long prevNum = minStack.peekLast()[1];
                long prevShares = minStack.pollLast()[2];
                minShares += prevShares;
                windowMin += (num - prevNum) * prevShares;
            }
            minStack.addLast(new long[] {end, num, minShares});
            total += windowMax + windowMin;
        }
        return total;
    }
}
