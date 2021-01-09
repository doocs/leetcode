class Solution {
    public void reverseString(char[] s) {
        int n;
        if (s == null || (n = s.length) < 2) return;
        int i = 0, j = n - 1;
        while (i < j) {
            char t = s[i];
            s[i] = s[j];
            s[j] = t;
            ++i;
            --j;
        }
    }
}