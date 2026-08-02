class Solution {
    public int countValidPrefixes(String s) {
        int ans = 0, t = 0;
        for (char c : s.toCharArray()) {
            t += c == '1' ? 1 : -1;
            if (Math.abs(t) <= 1) {
                ans++;
            }
        }
        return ans;
    }
}