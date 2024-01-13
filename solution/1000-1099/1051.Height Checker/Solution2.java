class Solution {
    public int heightChecker(int[] heights) {
        int[] cnt = new int[101];
        for (int h : heights) {
            ++cnt[h];
        }
        int ans = 0;
        for (int i = 0, j = 0; i < 101; ++i) {
            while (cnt[i] > 0) {
                --cnt[i];
                if (heights[j++] != i) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}