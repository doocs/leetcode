class Solution {
    public boolean scoreBalance(String s) {
        int n = s.length();
        int l = 0, r = 0;
        for (int i = 0; i < n; ++i) {
            int x = s.charAt(i) - 'a' + 1;
            r += x;
        }
        for (int i = 0; i < n - 1; ++i) {
            int x = s.charAt(i) - 'a' + 1;
            l += x;
            r -= x;
            if (l == r) {
                return true;
            }
        }
        return false;
    }
}
