class Solution {
    private int[] nums;
    private int k;
    private int m;

    public long countSubarrays(int[] nums, int k, int m) {
        this.nums = nums;
        this.k = k;
        this.m = m;
        return f(k) - f(k + 1);
    }

    private long f(int lim) {
        Map<Integer, Integer> cnt = new HashMap<>();
        long ans = 0;
        int l = 0;
        int t = 0;

        for (int x : nums) {
            if (cnt.merge(x, 1, Integer::sum) == m) {
                t++;
            }

            while (cnt.size() >= lim && t >= k) {
                int y = nums[l++];
                int cur = cnt.merge(y, -1, Integer::sum);
                if (cur == m - 1) {
                    --t;
                }
                if (cur == 0) {
                    cnt.remove(y);
                }
            }

            ans += l;
        }

        return ans;
    }
}
