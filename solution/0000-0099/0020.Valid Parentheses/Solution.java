class Solution {
    public boolean isValid(String s) {
        if (s == null || s == "") {
            return true;
        }
        char[] chars = s.toCharArray();
        Stack<Character> helper = new Stack<>();
        for (char c : chars) {
            boolean isLeft = c == '(' || c == '[' || c == '{';
            if (isLeft) {
                helper.push(c);
            } else {
                if (helper.isEmpty() || !match(helper.pop(), c)) {
                    return false;
                }
            }
        }
        return helper.isEmpty();
    }

    private boolean match(char left, char right) {
        return (left == '(' && right == ')') 
            || (left == '[' && right == ']')
            || (left == '{' && right == '}');
    }
}