class Solution {
    public int[] finalPrices(int[] prices) {
        Deque<Integer> stk = new ArrayDeque<>();
        int n = prices.length;
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            ans[i] = prices[i];
            while (!stk.isEmpty() && prices[stk.peek()] > prices[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                ans[i] -= prices[stk.peek()];
            }
            stk.push(i);
        }
        return ans;
    }
}