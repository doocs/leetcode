class Solution {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        if (colors[0] != colors[n - 1]) {
            return n - 1;
        }
        int i = 0, j = n - 1;
        while (colors[++i] == colors[0]);
        while (colors[--j] == colors[0]);
        return Math.max(n - i - 1, j);
    }
}