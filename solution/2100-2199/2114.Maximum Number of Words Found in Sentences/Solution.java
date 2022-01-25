class Solution {
    public int mostWordsFound(String[] sentences) {
        int ans = 0;
        for (String s : sentences) {
            ans = Math.max(ans, 1 + count(s, ' '));
        }
        return ans;
    }

    private int count(String s, char c) {
        int cnt = 0;
        for (char ch : s.toCharArray()) {
            if (ch == c) {
                ++cnt;
            }
        }
        return cnt;
    }
}