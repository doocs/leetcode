class Solution {
    public int countAsterisks(String s) {
        int ans = 0;
        for (int i = 0, ok = 1; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '*') {
                ans += ok;
            } else if (c == '|') {
                ok ^= 1;
            }
        }
        return ans;
    }
}