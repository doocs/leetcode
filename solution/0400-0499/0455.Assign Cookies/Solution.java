class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        for (; i < g.length; ++i) {
            while (j < s.length && s[j] < g[i]) {
                ++j;
            }
            if (j >= s.length) {
                break;
            }
            ++j;
        }
        return i;
    }
}