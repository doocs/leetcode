class Solution {
    public int calculate(String s) {
        Deque<Integer> stk = new ArrayDeque<>();
        int sign = 1;
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int j = i;
                int x = 0;
                while (j < n && Character.isDigit(s.charAt(j))) {
                    x = x * 10 + s.charAt(j) - '0';
                    j++;
                }
                ans += sign * x;
                i = j - 1;
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                stk.push(ans);
                stk.push(sign);
                ans = 0;
                sign = 1;
            } else if (c == ')') {
                ans = stk.pop() * ans + stk.pop();
            }
        }
        return ans;
    }
}