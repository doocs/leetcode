class Solution {
    public long minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Arrays.sort(horizontalCut);
        Arrays.sort(verticalCut);
        long ans = 0;
        int i = m - 2, j = n - 2;
        int h = 1, v = 1;
        while (i >= 0 || j >= 0) {
            if (j < 0 || (i >= 0 && horizontalCut[i] > verticalCut[j])) {
                ans += 1L * horizontalCut[i--] * v;
                ++h;
            } else {
                ans += 1L * verticalCut[j--] * h;
                ++v;
            }
        }
        return ans;
    }
}