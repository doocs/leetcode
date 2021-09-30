class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String t : tokens) {
            if (t.length() > 1 || Character.isDigit(t.charAt(0))) {
                stack.push(Integer.parseInt(t));
            } else {
                int y = stack.pop();
                int x = stack.pop();
                switch (t) {
                    case "+":
                        stack.push(x + y);
                        break;
                    case "-":
                        stack.push(x - y);
                        break;
                    case "*":
                        stack.push(x * y);
                        break;
                    default:
                        stack.push(x / y);
                        break;
                }
            }
        }
        return stack.pop();
    }
}
