class Solution {
    public String shortestPalindrome(String s) {
        int base = 131;
        int mul = 1;
        int mod = (int) 1e9 + 7;
        int prefix = 0, suffix = 0;
        int idx = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int t = s.charAt(i) - 'a' + 1;
            prefix = (int) (((long) prefix * base + t) % mod);
            suffix = (int) ((suffix + (long) t * mul) % mod);
            mul = (int) (((long) mul * base) % mod);
            if (prefix == suffix) {
                idx = i + 1;
            }
        }
        if (idx == n) {
            return s;
        }
        return new StringBuilder(s.substring(idx)).reverse().toString() + s;
    }
}