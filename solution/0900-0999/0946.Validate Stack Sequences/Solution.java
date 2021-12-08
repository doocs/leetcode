class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stk = new ArrayDeque<>();
        int j = 0, n = popped.length;
        for (int x : pushed) {
            stk.push(x);
            while (!stk.isEmpty() && j < n && stk.peek() == popped[j]) {
                stk.pop();
                ++j;
            }
        }
        return j == n;
    }
}