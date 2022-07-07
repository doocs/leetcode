class Solution {
    private int[][] f;
    private int[] locations;
    private int target;
    private static final int MOD = (int) 1e9 + 7;
    
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int n = locations.length;
        f = new int[n + 1][fuel + 1];
        this.locations = locations;
        target = finish;
        for (int i = 0; i < f.length; ++i) {
            Arrays.fill(f[i], -1);
        }
        return dfs(start, fuel);
    }

    private int dfs(int i, int t) {
        if (f[i][t] != -1) {
            return f[i][t];
        }
        if (Math.abs(locations[i] - locations[target]) > t) {
            return 0;
        }
        int res = i == target ? 1 : 0;
        for (int j = 0; j < locations.length; ++j) {
            if (j != i) {
                int cost = Math.abs(locations[i] - locations[j]);
                if (cost <= t) {
                    res += dfs(j, t - cost);
                    res %= MOD;
                }
            }
        }
        f[i][t] = res;
        return res;
    }
}