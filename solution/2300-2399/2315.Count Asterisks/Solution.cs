public class Solution {
    public int CountAsterisks(string s) {
        int ans = 0, ok = 1;
        foreach (char c in s) {
            if (c == '*') {
                ans += ok;
            } else if (c == '|') {
                ok ^= 1;
            }
        }
        return ans;
    }
}