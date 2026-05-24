class Solution {
    public int minOperations(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            nums[i] %= k;
        }

        int ans = Integer.MAX_VALUE;

        for (int x = 0; x < k; ++x) {
            for (int y = 0; y < k; ++y) {
                if (x != y) {
                    int cnt = 0;

                    for (int i = 0; i < n; ++i) {
                        int target = (i & 1) == 0 ? x : y;
                        int diff = Math.abs(target - nums[i]);
                        cnt += Math.min(diff, k - diff);
                    }

                    ans = Math.min(ans, cnt);
                }
            }
        }

        return ans;
    }
}