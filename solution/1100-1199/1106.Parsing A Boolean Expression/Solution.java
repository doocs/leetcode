class Solution {
    public boolean parseBoolExpr(String expression) {
        Deque<Character> stk = new ArrayDeque<>();
        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);
            if (c != '(' && c != ')' && c != ',') {
                stk.push(c);
            } else if (c == ')') {
                int t = 0, f = 0;
                while (stk.peek() == 't' || stk.peek() == 'f') {
                    t += stk.peek() == 't' ? 1 : 0;
                    f += stk.peek() == 'f' ? 1 : 0;
                    stk.pop();
                }
                char op = stk.pop();
                if (op == '!') {
                    c = f > 0 ? 't' : 'f';
                } else if (op == '&') {
                    c = f > 0 ? 'f' : 't';
                } else {
                    c = t > 0 ? 't' : 'f';
                }
                stk.push(c);
            }
        }
        return stk.peek() == 't';
    }
}