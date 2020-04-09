class Solution {
    public int scoreOfParentheses(String S) {
        int res = 0;
        for (int i = 0, d = 0; i < S.length(); ++i) {
            if (S.charAt(i) == '(') {
                ++d;
            } else {
                --d;
                if (S.charAt(i - 1) == '(') {
                    res += 1 << d;
                }
            }
        }
        return res;
    }
}
