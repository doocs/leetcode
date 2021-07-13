class Solution {
    public int trap(int[] height) {
        int n = height.length;
        if (n < 3) {
            return 0;
        }

        int[] lmx = new int[n];
        int[] rmx = new int[n];
        lmx[0] = height[0];
        rmx[n - 1] = height[n - 1];
        for (int i = 1; i < n; ++i) {
            lmx[i] = Math.max(lmx[i - 1], height[i]);
            rmx[n - 1 - i] = Math.max(rmx[n - i], height[n - i - 1]);
        }
        
        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += Math.min(lmx[i], rmx[i]) - height[i];
        }
        return res;
    }
}