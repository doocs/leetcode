class Solution {
    public int maxChunksToSorted(int[] arr) {
        Deque<Integer> stk = new ArrayDeque<>();
        for (int v : arr) {
            if (stk.isEmpty() || stk.peek() <= v) {
                stk.push(v);
            } else {
                int mx = stk.pop();
                while (!stk.isEmpty() && stk.peek() > v) {
                    stk.pop();
                }
                stk.push(mx);
            }
        }
        return stk.size();
    }
}