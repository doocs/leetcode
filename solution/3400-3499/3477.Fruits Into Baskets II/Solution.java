class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        boolean[] vis = new boolean[n];
        int ans = n;
        for (int x : fruits) {
            for (int i = 0; i < n; ++i) {
                if (baskets[i] >= x && !vis[i]) {
                    vis[i] = true;
                    --ans;
                    break;
                }
            }
        }
        return ans;
    }
}