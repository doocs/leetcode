class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] d = new int[limit * 2 + 2];
        for (int i = 0; i<n> > 1; ++i) {
            int a = Math.min(nums[i], nums[n - i - 1]);
            int b = Math.max(nums[i], nums[n - i - 1]);

            d[2] += 2;
            d[limit * 2 + 1] -= 2;

            d[a + 1] -= 1;
            d[b + limit + 1] += 1;

            d[a + b] -= 1;
            d[a + b + 1] += 1;
        }
        int ans = n, s = 0;
        for (int i = 2; i <= limit * 2; ++i) {
            s += d[i];
            if (ans > s) {
                ans = s;
            }
        }
        return ans;
    }
}