class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m > n) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < m; ++i) {
            --cnt[s1.charAt(i) - 'a'];
            ++cnt[s2.charAt(i) - 'a'];
        }
        int diff = 0;
        for (int x : cnt) {
            if (x != 0) {
                ++diff;
            }
        }
        if (diff == 0) {
            return true;
        }
        for (int i = m; i < n; ++i) {
            int a = s2.charAt(i - m) - 'a';
            int b = s2.charAt(i) - 'a';
            if (cnt[a] == 0) {
                ++diff;
            }
            --cnt[a];
            if (cnt[a] == 0) {
                --diff;
            }
            if (cnt[b] == 0) {
                ++diff;
            }
            ++cnt[b];
            if (cnt[b] == 0) {
                --diff;
            }
            if (diff == 0) {
                return true;
            }
        }
        return false;
    }
}