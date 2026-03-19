class Solution {
    private int n;
    private char[] s;

    public int almostPalindromic(String s) {
        n = s.length();
        this.s = s.toCharArray();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, f(i, i));
            ans = Math.max(ans, f(i, i + 1));
        }
        return ans;
    }

    private int f(int l, int r) {
        while (l >= 0 && r < n && s[l] == s[r]) {
            l--;
            r++;
        }

        int l1 = l - 1, r1 = r;
        int l2 = l, r2 = r + 1;

        while (l1 >= 0 && r1 < n && s[l1] == s[r1]) {
            l1--;
            r1++;
        }
        while (l2 >= 0 && r2 < n && s[l2] == s[r2]) {
            l2--;
            r2++;
        }

        return Math.min(n, Math.max(r1 - l1 - 1, r2 - l2 - 1));
    }
}
