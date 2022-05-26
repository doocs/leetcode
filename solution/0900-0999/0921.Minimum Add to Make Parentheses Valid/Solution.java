class Solution {
    public int minAddToMakeValid(String s) {
        Deque<Character> stk = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stk.push(c);
            } else {
                if (!stk.isEmpty() && stk.peek() == '(') {
                    stk.pop();
                } else {
                    stk.push(c);
                }
            }
        }
        return stk.size();
    }
}