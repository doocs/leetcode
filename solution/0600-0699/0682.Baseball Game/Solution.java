class Solution {
    public int calPoints(String[] ops) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String op : ops) {
            if ("C".equals(op)) {
                stack.pop();
            } else if ("D".equals(op)) {
                stack.push(stack.peek() << 1);
            } else if ("+".equals(op)) {
                Integer a = stack.pop();
                Integer b = stack.peek();
                stack.push(a);
                stack.push(a + b);
            } else {
                stack.push(Integer.valueOf(op));
            }
        }
        int res = 0;
        for (Integer score : stack) {
            res += score;
        }
        return res;
    }
}