class Solution {
    public int maximumGain(String s, int x, int y) {
        if (x < y) {
            return maximumGain(new StringBuilder(s).reverse().toString(), y, x);
        }
        int ans = 0;
        Deque<Character> stk1 = new ArrayDeque<>();
        Deque<Character> stk2 = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c != 'b') {
                stk1.push(c);
            } else {
                if (!stk1.isEmpty() && stk1.peek() == 'a') {
                    stk1.pop();
                    ans += x;
                } else {
                    stk1.push(c);
                }
            }
        }
        while (!stk1.isEmpty()) {
            char c = stk1.pop();
            if (c != 'b') {
                stk2.push(c);
            } else {
                if (!stk2.isEmpty() && stk2.peek() == 'a') {
                    stk2.pop();
                    ans += y;
                } else {
                    stk2.push(c);
                }
            }
        }
        return ans;
    }
}