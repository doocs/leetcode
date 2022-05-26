class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        int n = s1.length();
        int cnt = 0;
        char c1 = 0;
        char c2 = 0;
        for (int i = 0; i < n; ++i) {
            char t1 = s1.charAt(i), t2 = s2.charAt(i);
            if (t1 != t2) {
                ++cnt;
                if ((cnt == 2 && (c1 != t2 || c2 != t1)) || cnt > 2) {
                    return false;
                }
                c1 = t1;
                c2 = t2;
            }
        }
        return cnt == 0 || cnt == 2;
    }
}