class Solution {
    public int maxDigitRange(int[] nums) {
        int ans = 0, mx = 0;
        for (int x : nums) {
            int a = 10, b = 0;
            for (int y = x; y > 0; y /= 10) {
                int v = y % 10;
                a = Math.min(a, v);
                b = Math.max(b, v);
            }
            int r = b - a;
            if (mx < r) {
                mx = r;
                ans = x;
            } else if (mx == r) {
                ans += x;
            }
        }
        return ans;
    }
}