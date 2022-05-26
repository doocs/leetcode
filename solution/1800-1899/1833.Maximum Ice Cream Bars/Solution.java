class Solution {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int ans = 0, n = costs.length;
        for (int i = 0; i < n && coins >= costs[i]; i++) {
            ans++;
            coins -= costs[i];
        }
        return ans;
    }
}
