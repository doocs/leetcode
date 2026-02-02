class Solution {
    public String lexSmallestAfterDeletion(String s) {
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        StringBuilder stk = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            while (stk.length() > 0 && stk.charAt(stk.length() - 1) > c
                && cnt[stk.charAt(stk.length() - 1) - 'a'] > 1) {
                --cnt[stk.charAt(stk.length() - 1) - 'a'];
                stk.setLength(stk.length() - 1);
            }
            stk.append(c);
        }
        while (cnt[stk.charAt(stk.length() - 1) - 'a'] > 1) {
            --cnt[stk.charAt(stk.length() - 1) - 'a'];
            stk.setLength(stk.length() - 1);
        }
        return stk.toString();
    }
}
