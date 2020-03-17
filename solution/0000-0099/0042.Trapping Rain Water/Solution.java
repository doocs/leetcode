class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int lx = 0, rx = height.length - 1, l = height[lx], r = height[rx], re = 0;
        while (lx < rx) {
            if (l < r) {
                lx++;
                if (height[lx] < l) re += l - height[lx];
                else l = height[lx];
            } else {
                rx--;
                if (height[rx] < r) re += r - height[rx];
                else r = height[rx];
            }
        }
        return re;
    }
}