class Solution {
    public int secondsToRemoveOccurrences(String s) {
        int ans = 0, cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                ++cnt;
            } else if (cnt > 0) {
                ans = Math.max(ans + 1, cnt);
            }
        }
        return ans;
    }
}