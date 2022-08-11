class Solution {
    public String customSortString(String order, String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        StringBuilder ans = new StringBuilder();
        for (char c : order.toCharArray()) {
            while (cnt[c - 'a']-- > 0) {
                ans.append(c);
            }
        }
        for (char c : s.toCharArray()) {
            while (cnt[c - 'a']-- > 0) {
                ans.append(c);
            }
        }
        return ans.toString();
    }
}