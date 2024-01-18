class Solution {
    public boolean isAnagram(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m != n || s.equals(t)) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < m; ++i) {
            ++cnt[s.charAt(i) - 'a'];
            --cnt[t.charAt(i) - 'a'];
        }
        for (int x : cnt) {
            if (x != 0) {
                return false;
            }
        }
        return true;
    }
}