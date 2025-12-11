class Solution {
    private List<Integer>[] g;
    private int[] present;
    private int[] future;
    private int budget;

    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        this.present = present;
        this.future = future;
        this.budget = budget;

        g = new ArrayList[n + 1];
        Arrays.setAll(g, k -> new ArrayList<>());

        for (int[] e : hierarchy) {
            g[e[0]].add(e[1]);
        }

        return dfs(1)[budget][0];
    }

    private int[][] dfs(int u) {
        int[][] nxt = new int[budget + 1][2];

        for (int v : g[u]) {
            int[][] fv = dfs(v);
            for (int j = budget; j >= 0; j--) {
                for (int jv = 0; jv <= j; jv++) {
                    for (int pre = 0; pre < 2; pre++) {
                        int val = nxt[j - jv][pre] + fv[jv][pre];
                        if (val > nxt[j][pre]) {
                            nxt[j][pre] = val;
                        }
                    }
                }
            }
        }

        int[][] f = new int[budget + 1][2];
        int price = future[u - 1];

        for (int j = 0; j <= budget; j++) {
            for (int pre = 0; pre < 2; pre++) {
                int cost = present[u - 1] / (pre + 1);
                if (j >= cost) {
                    f[j][pre] = Math.max(nxt[j][0], nxt[j - cost][1] + (price - cost));
                } else {
                    f[j][pre] = nxt[j][0];
                }
            }
        }

        return f;
    }
}
