public class Solution {
    public int MaxDifference(string s) {
        int[] cnt = new int[26];
        foreach (char c in s) {
            ++cnt[c - 'a'];
        }
        int a = 0, b = 1 << 30;
        foreach (int v in cnt) {
            if (v % 2 == 1) {
                a = Math.Max(a, v);
            } else if (v > 0) {
                b = Math.Min(b, v);
            }
        }
        return a - b;
    }
}
