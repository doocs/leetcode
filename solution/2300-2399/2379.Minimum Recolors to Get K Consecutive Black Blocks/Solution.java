class Solution {
    public int minimumRecolors(String blocks, int k) {
        int cnt = 0, n = blocks.length();
        int i = 0;
        for (; i < k; ++i) {
            if (blocks.charAt(i) == 'W') {
                ++cnt;
            }
        }
        int ans = cnt;
        for (; i < n; ++i) {
            cnt += blocks.charAt(i) == 'W' ? 1 : 0;
            cnt -= blocks.charAt(i - k) == 'W' ? 1 : 0;
            ans = Math.min(ans, cnt);
        }
        return ans;
    }
}