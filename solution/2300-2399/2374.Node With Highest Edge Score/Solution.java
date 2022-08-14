class Solution {
    public int edgeScore(int[] edges) {
        int n = edges.length;
        long[] cnt = new long[n];
        for (int i = 0; i < n; ++i) {
            cnt[edges[i]] += i;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (cnt[ans] < cnt[i]) {
                ans = i;
            }
        }
        return ans;
    }
}