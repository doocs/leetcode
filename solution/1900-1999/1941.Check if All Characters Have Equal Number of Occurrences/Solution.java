class Solution {
    public boolean areOccurrencesEqual(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        int v = 0;
        for (int x : cnt) {
            if (x == 0) {
                continue;
            }
            if (v > 0 && v != x) {
                return false;
            }
            v = x;
        }
        return true;
    }
}
