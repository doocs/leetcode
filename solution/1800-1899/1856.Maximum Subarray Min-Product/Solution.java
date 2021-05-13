class Solution {
    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 1; i < n + 1; ++i) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int[] nextLesser = new int[n];
        Arrays.fill(nextLesser, n);
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                nextLesser[stack.pop()] = i;
            }
            stack.push(i);
        }

        stack = new ArrayDeque<>();
        int[] prevLesser = new int[n];
        Arrays.fill(prevLesser, -1);
        for (int i = n - 1; i >= 0; --i) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                prevLesser[stack.pop()] = i;
            }
            stack.push(i);
        }
        long res = 0;
        for (int i = 0; i < n; ++i) {
            int start = prevLesser[i], end = nextLesser[i];
            long t = nums[i] * (preSum[end] - preSum[start + 1]);
            res = Math.max(res, t);
        }
        return (int) (res % 1000000007);
    }
}