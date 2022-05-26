class Solution {
    public int calculate(String s) {
        int num = 0;
        char preSign = '+';
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0, n = s.length(); i < n; ++i) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + (s.charAt(i) - '0');
            }
            if (i == n - 1 || (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ')) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}