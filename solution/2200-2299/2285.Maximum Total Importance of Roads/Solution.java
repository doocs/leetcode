class Solution {
    public long maximumImportance(int n, int[][] roads) {
        int[] deg = new int[n];
        for (int[] r : roads) {
            ++deg[r[0]];
            ++deg[r[1]];
        }
        Arrays.sort(deg);
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += (long) (i + 1) * deg[i];
        }
        return ans;
    }
}