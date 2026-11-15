class Solution {
    public int countConsistent(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int y = x;
            for (int j = i; j < n; j++) {
                x &= nums[j];
                y = Math.min(y, nums[j]);
                if (x == y) {
                    ans++;
                }
            }
        }
        return ans;
    }
}