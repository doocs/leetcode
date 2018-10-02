class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String e : tokens) {
            if (isNum(e)) {
                stack.push(Integer.parseInt(e));
            } else {
                int y = stack.pop();
                int x = stack.pop();
                int z = 0;
                switch (e) {
                    case "+": z = x + y; break;
                    case "-": z = x - y; break;
                    case "*": z = x * y; break;
                    case "/": z = x / y; break;    
                }
                stack.push(z);
            }
        }
        return stack.peek();
    }
    
    private boolean isNum(String val) {
        try {
            Integer.parseInt(val);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}