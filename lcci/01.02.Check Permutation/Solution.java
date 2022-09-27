class Solution {
    public boolean CheckPermutation(String s1, String s2) {
        int[] cnt = new int[26];
        for (char c : s1.toCharArray()) {
            ++cnt[c - 'a'];
        }
        for (char c : s2.toCharArray()) {
            if (--cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}