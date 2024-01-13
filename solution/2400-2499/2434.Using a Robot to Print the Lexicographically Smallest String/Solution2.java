class Solution {
    public String robotWithString(String s) {
        int n = s.length();
        int[] right = new int[n];
        right[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; --i) {
            right[i] = s.charAt(i) < s.charAt(right[i + 1]) ? i : right[i + 1];
        }
        StringBuilder ans = new StringBuilder();
        Deque<Character> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            stk.push(s.charAt(i));
            while (
                !stk.isEmpty() && (stk.peek() <= (i > n - 2 ? 'z' + 1 : s.charAt(right[i + 1])))) {
                ans.append(stk.pop());
            }
        }
        return ans.toString();
    }
}