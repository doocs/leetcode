class Solution {
    public String lastNonEmptyString(String s) {
        int[] cnt = new int[26];
        int[] last = new int[26];
        int n = s.length();
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            int c = s.charAt(i) - 'a';
            mx = Math.max(mx, ++cnt[c]);
            last[c] = i;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            int c = s.charAt(i) - 'a';
            if (cnt[c] == mx && last[c] == i) {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }
}