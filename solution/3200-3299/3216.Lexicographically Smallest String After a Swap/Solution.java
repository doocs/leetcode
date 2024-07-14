class Solution {
    public String getSmallestString(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = 1; i < n; ++i) {
            char a = cs[i - 1], b = cs[i];
            if (a > b && a % 2 == b % 2) {
                cs[i] = a;
                cs[i - 1] = b;
                return new String(cs);
            }
        }
        return s;
    }
}