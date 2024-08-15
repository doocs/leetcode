class Solution {
    public String shortestPalindrome(String s) {
        char[] t = (s + "#" + new StringBuilder(s).reverse().toString() + "$").toCharArray();
        int n = t.length();
        int[] next = new int[n];
        next[0] = -1;
        for (int i = 2, j = 0; i < n;) {
            if (t[i - 1] == t[j]) {
                next[i++] = ++j;
            } else if (j > 0) {
                j = next[j];
            } else {
                next[i++] = 0;
            }
        }
        return new StringBuilder(s.substring(next[n - 1])).reverse().substring(0, s.length() - next[n - 1]) + s;
    }
}
