class Solution {
    public int maxDepth(String s) {
        int ans = 0, d = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                ans = Math.max(ans, ++d);
            } else if (c == ')') {
                --d;
            }
        }
        return ans;
    }
}