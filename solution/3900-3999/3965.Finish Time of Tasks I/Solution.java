class Solution {
    List<Integer>[] g;
    int[] baseTime;

    long dfs(int i) {
        if (g[i].isEmpty()) {
            return baseTime[i];
        }

        long earliest = Long.MAX_VALUE / 4;
        long latest = Long.MIN_VALUE / 4;

        for (int j : g[i]) {
            long a = dfs(j);
            earliest = Math.min(earliest, a);
            latest = Math.max(latest, a);
        }

        long ownDuration = (latest - earliest) + baseTime[i];
        return latest + ownDuration;
    }

    public long finishTime(int n, int[][] edges, int[] baseTime) {
        this.baseTime = baseTime;
        g = new ArrayList[n];
        Arrays.setAll(g, k -> new ArrayList<>());

        for (int[] e : edges) {
            g[e[0]].add(e[1]);
        }

        return dfs(0);
    }
}