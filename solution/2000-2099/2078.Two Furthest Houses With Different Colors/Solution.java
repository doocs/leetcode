class Solution {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        if (colors[0] != colors[n - 1]) {
            return n - 1;
        }
        int i = 1, j = n - 2;
        while (colors[i] == colors[0]) {
            ++i;
        }
        while (colors[j] == colors[0]) {
            --j;
        }
        return Math.max(n - i - 1, j);
    }
}
