class Solution {
    public String[] largestString(int[] nums) {
        int n = nums.length;
        String[] ans = new String[n];
        for (int k = 0; k < n; ++k) {
            int x = nums[k];
            StringBuilder s = new StringBuilder();
            for (int j = 25; j >= 0; --j) {
                for (int t = x >> j; t > 0; --t) {
                    s.append((char) ('a' + j));
                }
                x &= (1 << j) - 1;
            }
            ans[k] = s.toString();
        }
        return ans;
    }
}
