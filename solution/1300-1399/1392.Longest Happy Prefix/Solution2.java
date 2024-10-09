class Solution {
    public String longestPrefix(String s) {
        s += "#";
        int n = s.length();
        int[] next = new int[n];
        next[0] = -1;
        for (int i = 2, j = 0; i < n;) {
            if (s.charAt(i - 1) == s.charAt(j)) {
                next[i++] = ++j;
            } else if (j > 0) {
                j = next[j];
            } else {
                next[i++] = 0;
            }
        }
        return s.substring(0, next[n - 1]);
    }
}
