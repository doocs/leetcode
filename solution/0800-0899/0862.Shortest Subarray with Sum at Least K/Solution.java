class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        Deque<Integer> q = new ArrayDeque<>();
        int ans = n + 1;
        for (int i = 0; i <= n; ++i) {
            while (!q.isEmpty() && s[i] - s[q.peek()] >= k) {
                ans = Math.min(ans, i - q.poll());
            }
            while (!q.isEmpty() && s[q.peekLast()] >= s[i]) {
                q.pollLast();
            }
            q.offer(i);
        }
        return ans > n ? -1 : ans;
    }
}