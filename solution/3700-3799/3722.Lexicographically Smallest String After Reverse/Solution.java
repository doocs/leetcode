class Solution {
    public String lexSmallest(String s) {
        String ans = s;
        int n = s.length();
        for (int k = 1; k <= n; ++k) {
            String t1 = new StringBuilder(s.substring(0, k)).reverse().toString() + s.substring(k);
            String t2 = s.substring(0, n - k)
                + new StringBuilder(s.substring(n - k)).reverse().toString();
            if (t1.compareTo(ans) < 0) {
                ans = t1;
            }
            if (t2.compareTo(ans) < 0) {
                ans = t2;
            }
        }
        return ans;
    }
}
