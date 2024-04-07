class Solution {
    public String getSmallestString(String s, int k) {
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; ++i) {
            char c1 = cs[i];
            for (char c2 = 'a'; c2 < c1; ++c2) {
                int d = Math.min(c1 - c2, 26 - c1 + c2);
                if (d <= k) {
                    cs[i] = c2;
                    k -= d;
                    break;
                }
            }
        }
        return new String(cs);
    }
}