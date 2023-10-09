class Solution {
    public int calculate(String s) {
        int n = s.length();
        int x = 0;
        char sign = '+';
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                x = x * 10 + (c - '0');
            }
            if (i == n - 1 || !Character.isDigit(c) && c != ' ') {
                switch (sign) {
                    case '+' -> stk.push(x);
                    case '-' -> stk.push(-x);
                    case '*' -> stk.push(stk.pop() * x);
                    case '/' -> stk.push(stk.pop() / x);
                }
                x = 0;
                sign = c;
            }
        }
        int ans = 0;
        while (!stk.isEmpty()) {
            ans += stk.pop();
        }
        return ans;
    }
}