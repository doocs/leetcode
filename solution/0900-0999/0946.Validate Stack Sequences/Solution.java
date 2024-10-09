class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stk = new ArrayDeque<>();
        int i = 0;
        for (int x : pushed) {
            stk.push(x);
            while (!stk.isEmpty() && stk.peek() == popped[i]) {
                stk.pop();
                ++i;
            }
        }
        return i == popped.length;
    }
}