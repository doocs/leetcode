class Solution {
    public int countPalindromicSubsequence(String s) {
        int ans = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            int l = s.indexOf(c), r = s.lastIndexOf(c);
            Set<Character> cs = new HashSet<>();
            for (int i = l + 1; i < r; ++i) {
                cs.add(s.charAt(i));
            }
            ans += cs.size();
        }
        return ans;
    }
}