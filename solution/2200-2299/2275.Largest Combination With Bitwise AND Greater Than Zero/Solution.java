class Solution {
    public int largestCombination(int[] candidates) {
        int mx = Arrays.stream(candidates).max().getAsInt();
        int m = Integer.SIZE - Integer.numberOfLeadingZeros(mx);
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            int cnt = 0;
            for (int x : candidates) {
                cnt += x >> i & 1;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
