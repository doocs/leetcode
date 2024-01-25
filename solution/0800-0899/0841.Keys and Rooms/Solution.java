class Solution {
    private int cnt;
    private boolean[] vis;
    private List<List<Integer>> g;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        g = rooms;
        vis = new boolean[g.size()];
        dfs(0);
        return cnt == g.size();
    }

    private void dfs(int i) {
        if (vis[i]) {
            return;
        }
        vis[i] = true;
        ++cnt;
        for (int j : g.get(i)) {
            dfs(j);
        }
    }
}