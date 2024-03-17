class Solution {
    public boolean isSubstringPresent(String s) {
        boolean[][] st = new boolean[26][26];
        int n = s.length();
        for (int i = 0; i < n - 1; ++i) {
            st[s.charAt(i + 1) - 'a'][s.charAt(i) - 'a'] = true;
        }
        for (int i = 0; i < n - 1; ++i) {
            if (st[s.charAt(i) - 'a'][s.charAt(i + 1) - 'a']) {
                return true;
            }
        }
        return false;
    }
}