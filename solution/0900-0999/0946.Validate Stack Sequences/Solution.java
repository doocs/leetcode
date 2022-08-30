class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stk = new ArrayDeque<>();
        int j = 0;
        for (int v : pushed) {
            stk.push(v);
            while (!stk.isEmpty() && stk.peek() == popped[j]) {
                stk.pop();
                ++j;
            }
        }
        return j == pushed.length;
    }
}