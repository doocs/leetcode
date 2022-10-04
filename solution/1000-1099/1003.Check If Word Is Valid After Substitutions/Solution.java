class Solution {
    public boolean isValid(String s) {
        if (s.length() % 3 > 0) {
            return false;
        }
        StringBuilder stk = new StringBuilder();
        for (char c : s.toCharArray()) {
            int n = stk.length();
            if (c == 'c' && n > 1 && stk.charAt(n - 2) == 'a' && stk.charAt(n - 1) == 'b') {
                stk.deleteCharAt(n - 1);
                stk.deleteCharAt(n - 2);
            } else {
                stk.append(c);
            }
        }
        return stk.length() == 0;
    }
}