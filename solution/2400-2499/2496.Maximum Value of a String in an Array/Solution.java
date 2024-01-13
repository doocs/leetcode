class Solution {
    public int maximumValue(String[] strs) {
        int ans = 0;
        for (var s : strs) {
            ans = Math.max(ans, f(s));
        }
        return ans;
    }

    private int f(String s) {
        int x = 0;
        for (int i = 0, n = s.length(); i < n; ++i) {
            char c = s.charAt(i);
            if (Character.isLetter(c)) {
                return n;
            }
            x = x * 10 + (c - '0');
        }
        return x;
    }
}