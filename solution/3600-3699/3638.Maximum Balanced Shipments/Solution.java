class Solution {
    public int maxBalancedShipments(int[] weight) {
        int ans = 0;
        int mx = 0;
        for (int x : weight) {
            mx = Math.max(mx, x);
            if (x < mx) {
                ++ans;
                mx = 0;
            }
        }
        return ans;
    }
}