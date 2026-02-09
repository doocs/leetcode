class Solution {
    public long countSubarrays(int[] nums, long k) {
        long ans = 0;
        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();
        int l = 0;

        for (int r = 0; r < nums.length; r++) {
            int x = nums[r];

            while (!q1.isEmpty() && nums[q1.peekLast()] <= x) {
                q1.pollLast();
            }
            while (!q2.isEmpty() && nums[q2.peekLast()] >= x) {
                q2.pollLast();
            }
            q1.addLast(r);
            q2.addLast(r);

            while (
                l < r && (long) (nums[q1.peekFirst()] - nums[q2.peekFirst()]) * (r - l + 1) > k) {
                l++;
                if (q1.peekFirst() < l) {
                    q1.pollFirst();
                }
                if (q2.peekFirst() < l) {
                    q2.pollFirst();
                }
            }

            ans += r - l + 1;
        }
        return ans;
    }
}
