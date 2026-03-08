class Solution {
    private int[] pre;
    private int encCost;
    private int flatCost;

    public long minCost(String s, int encCost, int flatCost) {
        int n = s.length();
        this.encCost = encCost;
        this.flatCost = flatCost;

        pre = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            pre[i] = pre[i - 1] + (s.charAt(i - 1) - '0');
        }

        return dfs(0, n);
    }

    private long dfs(int l, int r) {
        int x = pre[r] - pre[l];
        long res = x != 0 ? (long) (r - l) * x * encCost : flatCost;

        if ((r - l) % 2 == 0) {
            int m = (l + r) >> 1;
            res = Math.min(res, dfs(l, m) + dfs(m, r));
        }

        return res;
    }
}
