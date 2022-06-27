class Solution {
    public boolean areOccurrencesEqual(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        int t = 0;
        for (int v : cnt) {
            if (v == 0) {
                continue;
            }
            if (t == 0) {
                t = v;
            } else if (t != v) {
                return false;
            }
        }
        return true;
    }
}