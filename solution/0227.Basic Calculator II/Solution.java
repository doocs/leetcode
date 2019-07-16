class Solution {
    public int calculate(String s) {
        char[] cs = s.toCharArray();
        Deque<Character> op = new ArrayDeque<>();
        Deque<Integer> num = new ArrayDeque<>();
        for (int i = 0; i < cs.length; ++i) {
            if (cs[i] == '*' || cs[i] == '/') {
                op.push(cs[i]);
            } else if (cs[i] == '+' || cs[i] == '-') {
                if (!op.isEmpty()) {
                    calc(op, num);
                }
                op.push(cs[i]);
            } else if (Character.isDigit(cs[i])) {
                int j = i;
                int k = 0;
                while (j < cs.length && Character.isDigit(cs[j])) {
                    k = k * 10 + cs[j] - '0';
                    ++j;
                }
                i = j - 1;
                num.push(k);
                if (!op.isEmpty() && (op.peek() == '*' || op.peek() == '/')) {
                    calc(op, num);
                }
            }
        }
        while (!op.isEmpty()) {
            calc(op, num);
        }
        return num.peek();
    }

    private void calc(Deque<Character> op, Deque<Integer> num) {
        int y = num.pop();
        int x = num.pop();
        switch (op.pop()) {
            case '*':
                num.push(x * y);
                break;
            case '/':
                num.push(x / y);
                break;
            case '+':
                num.push(x + y);
                break;
            default:
                num.push(x - y);
                break;
        }
    }
}
