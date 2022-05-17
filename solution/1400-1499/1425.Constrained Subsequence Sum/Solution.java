class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        int ans = Integer.MIN_VALUE;
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (!q.isEmpty() && i - q.peek() > k) {
                q.poll();
            }
            dp[i] = Math.max(0, q.isEmpty() ? 0 : dp[q.peek()]) + nums[i];
            while (!q.isEmpty() && dp[q.peekLast()] <= dp[i]) {
                q.pollLast();
            }
            q.offer(i);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}