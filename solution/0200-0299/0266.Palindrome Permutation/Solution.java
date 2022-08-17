class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        int n = 0;
        for (int v : cnt) {
            n += v % 2;
        }
        return n < 2;
    }
}