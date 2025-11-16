class Solution {
    public int minLengthAfterRemovals(String s) {
        int n = s.length();
        int a = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == 'a') {
                ++a;
            }
        }
        int b = n - a;
        return Math.abs(a - b);
    }
}
