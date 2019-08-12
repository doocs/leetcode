class Solution {
    public int longestValidParentheses(String s) {
        int res = 0;
        for (int i = 0, left = 0, right = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                ++left;
            } else {
                ++right;
            }
            if (left == right) {
                res = Math.max(res, left << 1);
            } else if (left < right) {
                left = 0;
                right = 0;
            }
        }
        for (int i = s.length() - 1, left = 0, right = 0; i >= 0; --i) {
            if (s.charAt(i) == '(') {
                ++left;
            } else {
                ++right;
            }
            if (left == right) {
                res = Math.max(res, left << 1);
            } else if (left > right) {
                left = 0;
                right = 0;
            }
        }
        return res;
    }
}
