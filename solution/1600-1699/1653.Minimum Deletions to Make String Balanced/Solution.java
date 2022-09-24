class Solution {
    public int minimumDeletions(String s) {
        int ans = 0, b = 0;
        for (char c : s.toCharArray()) {
            if (c == 'b') {
                ++b;
            } else {
                ans = Math.min(b, ans + 1);
            }
        }
        return ans;
    }
}