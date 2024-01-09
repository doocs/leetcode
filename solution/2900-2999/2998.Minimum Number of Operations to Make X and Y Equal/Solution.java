class Solution {
    private Map<Integer, Integer> f = new HashMap<>();
    private int y;

    public int minimumOperationsToMakeEqual(int x, int y) {
        this.y = y;
        return dfs(x);
    }

    private int dfs(int x) {
        if (y >= x) {
            return y - x;
        }
        if (f.containsKey(x)) {
            return f.get(x);
        }
        int ans = x - y;
        int a = x % 5 + 1 + dfs(x / 5);
        int b = 5 - x % 5 + 1 + dfs(x / 5 + 1);
        int c = x % 11 + 1 + dfs(x / 11);
        int d = 11 - x % 11 + 1 + dfs(x / 11 + 1);
        ans = Math.min(ans, Math.min(a, Math.min(b, Math.min(c, d))));
        f.put(x, ans);
        return ans;
    }
}