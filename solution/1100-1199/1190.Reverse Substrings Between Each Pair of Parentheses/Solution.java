class Solution {
    public String reverseParentheses(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == ')') {
                StringBuilder sb = new StringBuilder();
                while (deque.peekLast() != '(') {
                    sb.append(deque.pollLast());
                }
                deque.pollLast();
                for (int i = 0, n = sb.length(); i < n; i++) {
                    deque.offerLast(sb.charAt(i));
                }
            } else {
                deque.offerLast(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }
        return sb.toString();
    }
}
