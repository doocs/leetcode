class Solution {
    public int maxDepth(String s) {
        int n = 0, ans = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                ans = Math.max(ans, ++n);
            } else if (c == ')') {
                --n;
            }
        }
        return ans;
    }
}