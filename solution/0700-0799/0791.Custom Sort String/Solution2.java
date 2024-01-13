class Solution {
    public String customSortString(String order, String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < order.length(); ++i) {
            char c = order.charAt(i);
            while (cnt[c - 'a']-- > 0) {
                ans.append(c);
            }
        }
        for (int i = 0; i < 26; ++i) {
            while (cnt[i]-- > 0) {
                ans.append((char) ('a' + i));
            }
        }
        return ans.toString();
    }
}