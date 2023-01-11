class Solution {
    public int maximumSum(int[] nums) {
        int ans = -1;
        int[] d = new int[100];
        for (int v : nums) {
            int y = 0;
            for (int x = v; x > 0; x /= 10) {
                y += x % 10;
            }
            if (d[y] > 0) {
                ans = Math.max(ans, d[y] + v);
            }
            d[y] = Math.max(d[y], v);
        }
        return ans;
    }
}