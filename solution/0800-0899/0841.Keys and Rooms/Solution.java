class Solution {
    private List<List<Integer>> rooms;
    private Set<Integer> vis;
    private int n;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        vis = new HashSet<>();
        this.rooms = rooms;
        n = rooms.size();
        dfs(0);
        return vis.size() == n;
    }

    private void dfs(int u) {
        if (u == n || vis.contains(u)) {
            return;
        }
        vis.add(u);
        for (int v : rooms.get(u)) {
            dfs(v);
        }
    }
}