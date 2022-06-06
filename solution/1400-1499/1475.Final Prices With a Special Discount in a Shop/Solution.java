class Solution {
    public int[] finalPrices(int[] prices) {
        Deque<Integer> stk = new ArrayDeque<>();
        int n = prices.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = prices[i];
            while (!stk.isEmpty() && prices[stk.peek()] >= prices[i]) {
                ans[stk.pop()] -= prices[i];
            }
            stk.push(i);
        }
        return ans;
    }
}