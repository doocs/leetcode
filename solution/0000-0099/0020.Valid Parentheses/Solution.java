class Solution {
    public boolean isValid(String s) {
        Deque<Character> stk = new ArrayDeque<>();
        Map<Character, Character> d = new HashMap<>(3);
        d.put('(', ')');
        d.put('[', ']');
        d.put('{', '}');
        for (char c : s.toCharArray()) {
            if (d.containsKey(c)) {
                stk.push(d.get(c));
            } else if (stk.isEmpty() || stk.pop() != c) {
                return false;
            }
        }
        return stk.isEmpty();
    }
}
