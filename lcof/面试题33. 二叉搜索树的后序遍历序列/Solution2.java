class Solution {
    public boolean verifyPostorder(int[] postorder) {
        int mx = 1 << 30;
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = postorder.length - 1; i >= 0; --i) {
            int x = postorder[i];
            if (x > mx) {
                return false;
            }
            while (!stk.isEmpty() && stk.peek() > x) {
                mx = stk.pop();
            }
            stk.push(x);
        }
        return true;
    }
}