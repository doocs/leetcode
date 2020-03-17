class Solution {
    public int calculate(String s) {
        char[] cs = s.toCharArray();
        Deque<Character> op = new ArrayDeque<>();
        Deque<Integer> num = new ArrayDeque<>();
        for (int i = 0; i < cs.length; ++i) {
            if (cs[i] == '(' || cs[i] == '+' || cs[i] == '-') {
                op.push(cs[i]);
            } else if (cs[i] == ')') {
                op.pop();
                if (!op.isEmpty() && op.peek() != '(') {
                    calc(op, num);
                }
            } else if (Character.isDigit(cs[i])) {
                int j = i;
                int k = 0;
                while (j < cs.length && Character.isDigit(cs[j])) {
                    k = k * 10 + cs[j] - '0';
                    ++j;
                }
                num.push(k);
                i = j - 1;
                if (!op.isEmpty() && op.peek() != '(') {
                    calc(op, num);
                }
            }
        }
        return num.peek();
    }

    private void calc(Deque<Character> op, Deque<Integer> num) {
        int y = num.pop();
        int x = num.pop();
        if (op.pop() == '+') {
            num.push(x + y);
        } else {
            num.push(x - y);
        }
    }
}
