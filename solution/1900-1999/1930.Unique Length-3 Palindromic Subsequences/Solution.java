class Solution {
    public int countPalindromicSubsequence(String s) {
        int res = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            int l = s.indexOf(c), r = s.lastIndexOf(c);
            Set<Character> chars = new HashSet<>();
            for (int i = l + 1; i < r; ++i) {
                chars.add(s.charAt(i));
            }
            res += chars.size();
        }
        return res;
    }
}