class Solution {
    private Map<Integer, List<Integer>> g = new HashMap<>();
    private int[] ans;

    public int[] restoreArray(int[][] adjacentPairs) {
        for (var e : adjacentPairs) {
            int a = e[0], b = e[1];
            g.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            g.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }
        int n = adjacentPairs.length + 1;
        ans = new int[n];
        for (var e : g.entrySet()) {
            if (e.getValue().size() == 1) {
                dfs(e.getKey(), 1000000, 0);
                break;
            }
        }
        return ans;
    }

    private void dfs(int i, int fa, int k) {
        ans[k++] = i;
        for (int j : g.get(i)) {
            if (j != fa) {
                dfs(j, i, k);
            }
        }
    }
}