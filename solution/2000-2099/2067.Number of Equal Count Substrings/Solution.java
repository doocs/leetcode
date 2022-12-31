class Solution {
    public int equalCountSubstrings(String s, int count) {
        int ans = 0;
        int n = s.length();
        for (int x = 1; x < 27 && count * x <= n; ++x) {
            int m = count * x;
            int[] cnt = new int[26];
            int y = 0;
            for (int i = 0; i < n; ++i) {
                int a = s.charAt(i) - 'a';
                ++cnt[a];
                if (cnt[a] == count) {
                    ++y;
                }
                if (cnt[a] == count + 1) {
                    --y;
                }
                int j = i - m;
                if (j >= 0) {
                    int b = s.charAt(j) - 'a';
                    --cnt[b];
                    if (cnt[b] == count) {
                        ++y;
                    }
                    if (cnt[b] == count - 1) {
                        --y;
                    }
                }
                if (x == y) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}