class Solution {
    public boolean areOccurrencesEqual(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        int x = 0;
        for (int v : cnt) {
            if (v > 0) {
                if (x == 0) {
                    x = v;
                } else if (x != v) {
                    return false;
                }
            }
        }
        return true;
    }
}