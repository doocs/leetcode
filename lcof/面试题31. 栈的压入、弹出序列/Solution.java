class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> t = new Stack<>();
        int i = 0, n = pushed.length;
        for (int num : popped) {
            while (t.empty() || t.peek() != num) {
                if (i == n) {
                    return false;
                }
                t.push(pushed[i++]);
            }
            t.pop();
        }
        return true;
    }
}