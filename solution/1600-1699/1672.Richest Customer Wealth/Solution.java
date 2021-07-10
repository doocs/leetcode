class Solution {
    public int maximumWealth(int[][] accounts) {
        int res = 0;
        for (int[] account : accounts) {
            int t = 0;
            for (int money : account) {
                t += money;
            }
            res = Math.max(res, t);
        }
        return res;
    }
}