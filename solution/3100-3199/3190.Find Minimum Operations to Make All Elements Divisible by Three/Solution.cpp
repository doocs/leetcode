class Solution {
    public int minimumOperations(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            int mod = x % 3;
            if (mod != 0) {
                ans += Math.min(mod, 3 - mod);
            }
        }
        return ans;
    }
}