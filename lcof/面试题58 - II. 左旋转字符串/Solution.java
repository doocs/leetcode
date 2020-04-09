class Solution {
    public String reverseLeftWords(String s, int n) {
        int len = s.length();
        n %= len;
        return s.substring(n, len) + s.substring(0, n);
    }
}