public class Solution {
    public int NumDecodings(string s) {
        int n = s.Length;
        int f = 0, g = 1;
        for (int i = 1; i <= n; ++i) {
            int h = s[i - 1] != '0' ? g : 0;
            if (i > 1 && (s[i - 2] == '1' || (s[i - 2] == '2' && s[i - 1] <= '6'))) {
                h += f;
            }
            f = g;
            g = h;
        }
        return g;
    }
}