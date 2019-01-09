class Solution {
    public int maxArea(int[] height) {
        int start = 0, end = height.length - 1, maxArea = 0;
        while (start < end) {
            int hs = height[start];
            int he = height[end];
            int l = end - start;
            if (hs > he) {
                maxArea = Math.max(he * l, maxArea);
                end--;
            } else {
                maxArea = Math.max(hs * l, maxArea);
                start++;
            }
        }
        return maxArea;
    }
}
