class Solution {
    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        int x = sideLength;
        int[] cnt = new int[x * x];
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                int k = (i % x) * x + (j % x);
                ++cnt[k];
            }
        }
        Arrays.sort(cnt);
        int ans = 0;
        for (int i = 0; i < maxOnes; ++i) {
            ans += cnt[cnt.length - i - 1];
        }
        return ans;
    }
}