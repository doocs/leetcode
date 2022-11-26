class Solution {
    private int k;

    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        this.k = k;
        List<Integer> row = f(rowConditions);
        List<Integer> col = f(colConditions);
        if (row == null || col == null) {
            return new int[0][0];
        }
        int[][] ans = new int[k][k];
        int[] m = new int[k + 1];
        for (int i = 0; i < k; ++i) {
            m[col.get(i)] = i;
        }
        for (int i = 0; i < k; ++i) {
            ans[i][m[row.get(i)]] = row.get(i);
        }
        return ans;
    }

    private List<Integer> f(int[][] cond) {
        List<Integer>[] g = new List[k + 1];
        Arrays.setAll(g, key -> new ArrayList<>());
        int[] indeg = new int[k + 1];
        for (var e : cond) {
            int a = e[0], b = e[1];
            g[a].add(b);
            ++indeg[b];
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i < indeg.length; ++i) {
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            for (int n = q.size(); n > 0; --n) {
                int i = q.pollFirst();
                res.add(i);
                for (int j : g[i]) {
                    if (--indeg[j] == 0) {
                        q.offer(j);
                    }
                }
            }
        }
        return res.size() == k ? res : null;
    }
}