class Solution {
    public int maxSum(int[] nums) {
        int mx = Arrays.stream(nums).max().getAsInt();
        if (mx <= 0) {
            return mx;
        }
        boolean[] s = new boolean[201];
        int ans = 0;
        for (int x : nums) {
            if (x < 0 || s[x]) {
                continue;
            }
            ans += x;
            s[x] = true;
        }
        return ans;
    }
}
