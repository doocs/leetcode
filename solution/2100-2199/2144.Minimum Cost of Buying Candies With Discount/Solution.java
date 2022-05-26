class Solution {
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);
        int ans = 0, n = cost.length;
        for (int i = n - 1; i >= 0; i -= 3) {
            ans += cost[i];
            if (i >= 1) {
                ans += cost[i - 1];
            }
        }
        return ans;
    }
}