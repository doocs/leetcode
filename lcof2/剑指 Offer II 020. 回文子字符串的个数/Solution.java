class Solution {
    private String s;

    public int countSubstrings(String s) {
        int ans = 0;
        this.s = s;
        for (int i = 0; i < s.length(); ++i) {
            ans += f(i, i);
            ans += f(i, i + 1);
        }
        return ans;
    }

    private int f(int i, int j) {
        int cnt = 0;
        for (; i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j); --i, ++j) {
            ++cnt;
        }
        return cnt;
    }
}