class Solution {
    public int minNumBooths(String[] demand) {
        int[] cnt = new int[26];
        int ans = 0;
        for (var s : demand) {
            int m = s.length();
            for (int i = 0; i < m; ++i) {
                int j = s.charAt(i) - 'a';
                if (cnt[j] > 0) {
                    --cnt[j];
                } else {
                    ++ans;
                }
            }
            for (int i = 0; i < m; ++i) {
                ++cnt[s.charAt(i) - 'a'];
            }
        }
        return ans;
    }
}