class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> q = new ArrayDeque<>();
        long ans = 0;
        long cost = 0;
        int r = n - 1;
        for (int l = n - 1; l >= 0; --l) {
            int x = nums[l];
            while (!q.isEmpty() && nums[q.peekLast()] <= x) {
                int s = q.pollLast();
                int end = q.isEmpty() ? r + 1 : q.peekLast();
                cost += (long) (end - s) * (x - nums[s]);
            }
            q.offerLast(l);
            while (cost > k) {
                cost -= nums[q.peekFirst()] - nums[r];
                if (q.peekFirst() == r) {
                    q.pollFirst();
                }
                --r;
            }
            ans += r - l + 1;
        }
        return ans;
    }
}
