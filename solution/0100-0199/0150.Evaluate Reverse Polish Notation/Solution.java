class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> s = new ArrayDeque<>();
        int left, right;
        for (String token : tokens) {
            switch(token) {
            case "+":
                right = s.pop();
                left = s.pop();
                s.push(left + right);
                break;
            case "-":
                right = s.pop();
                left = s.pop();
                s.push(left - right);
                break;
            case "*":
                right = s.pop();
                left = s.pop();
                s.push(left * right);
                break;
            case "/":
                right = s.pop();
                left = s.pop();
                s.push(left / right);
                break;
            default:
                s.push(Integer.valueOf(token));
            }
        }
        return s.pop();
    }
}