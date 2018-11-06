class Solution {
    public boolean isValid(String s) {
        if (s == null || s == "") {
            return true;
        }
        char[] chars = s.toCharArray();
        int n = chars.length;

        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < n; ++i) {
            char a = chars[i];
            if (isLeft(a)) {
                stack.push(a);
            } else {
                if (stack.isEmpty() || !isMatch(stack.pop(), a)) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
                
    }
    
    private boolean isMatch(char a, char b) {
        return (a == '(' && b == ')') || (a == '[' && b == ']') || (a == '{' && b == '}');
    }
    
    private boolean isLeft(char a) {
        return a == '(' || a == '[' || a == '{';
    }
    
    private boolean isRight(char a) {
        return a == ')' || a == ']' || a == '}';
    }
}