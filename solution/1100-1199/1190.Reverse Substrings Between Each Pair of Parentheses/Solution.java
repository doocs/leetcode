class Solution {
    public String reverseParentheses(String s) {
        StringBuilder stk = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ')') {
                StringBuilder t = new StringBuilder();
                while (stk.charAt(stk.length() - 1) != '(') {
                    t.append(stk.charAt(stk.length() - 1));
                    stk.deleteCharAt(stk.length() - 1);
                }
                stk.deleteCharAt(stk.length() - 1);
                stk.append(t);
            } else {
                stk.append(c);
            }
        }
        return stk.toString();
    }
}
