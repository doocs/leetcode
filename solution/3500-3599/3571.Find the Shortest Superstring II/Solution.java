class Solution {
    public String shortestSuperstring(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        if (m > n) {
            return shortestSuperstring(s2, s1);
        }
        if (s2.contains(s1)) {
            return s2;
        }
        for (int i = 0; i < m; i++) {
            if (s2.startsWith(s1.substring(i))) {
                return s1.substring(0, i) + s2;
            }
            if (s2.endsWith(s1.substring(0, m - i))) {
                return s2 + s1.substring(m - i);
            }
        }
        return s1 + s2;
    }
}
