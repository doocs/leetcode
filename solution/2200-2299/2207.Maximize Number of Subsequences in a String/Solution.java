class Solution {
    public long maximumSubsequenceCount(String text, String pattern) {
        long ans = 0;
        int x = 0, y = 0;
        for (int i = 0; i < text.length(); ++i) {
            if (text.charAt(i) == pattern.charAt(1)) {
                ++y;
                ans += x;
            }
            if (text.charAt(i) == pattern.charAt(0)) {
                ++x;
            }
        }
        ans += Math.max(x, y);
        return ans;
    }
}
