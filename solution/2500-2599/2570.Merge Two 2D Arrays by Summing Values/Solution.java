class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int[] cnt = new int[1001];
        for (var x : nums1) {
            cnt[x[0]] += x[1];
        }
        for (var x : nums2) {
            cnt[x[0]] += x[1];
        }
        int n = 0;
        for (int i = 0; i < 1001; ++i) {
            if (cnt[i] > 0) {
                ++n;
            }
        }
        int[][] ans = new int[n][2];
        for (int i = 0, j = 0; i < 1001; ++i) {
            if (cnt[i] > 0) {
                ans[j++] = new int[] {i, cnt[i]};
            }
        }
        return ans;
    }
}